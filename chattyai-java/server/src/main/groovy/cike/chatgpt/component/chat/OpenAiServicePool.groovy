package cike.chatgpt.component.chat

import cike.chatgpt.config.OpenAIConfig
import cike.chatgpt.repository.OpenAIKeyRepository
import cike.chatgpt.utils.RandomUtil
import cike.openai.ChattyAIService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct
import java.time.Duration
import java.util.stream.Collectors

@Component
class OpenAiServicePool {

  @Autowired
  private OpenAIConfig openAIConfig

  @Autowired
  private OpenAIKeyRepository repository

  List<ChattyAIService> enabledKeys = new ArrayList<>()

  Map<String, ChattyAIService> openAiServiceMap = new HashMap<>()

  @PostConstruct
  void init() {
    refresh()
  }

  ChattyAIService getOne() {
    if (enabledKeys.size() == 0) {
      return null
    } else if (enabledKeys.size() == 1) {
      return enabledKeys.get(0)
    } else {
      int randomIndex = RandomUtil.nextInt(0, enabledKeys.size())
      return enabledKeys.get(randomIndex)
    }
  }

  @Scheduled(cron = "0 0/5 * * * ?")
  void refresh() {
    def keyConfigs = repository.findEnableKey()
    if (keyConfigs == null || keyConfigs.size() == 0) {
      enabledKeys.clear()
    } else {
      List<ChattyAIService> refreshKeys = new ArrayList<>(keyConfigs.size())
      Set<String> currentKeys = new HashSet<>()
      for (final def config in keyConfigs) {
        String openAIKey = config.openaiKey
        currentKeys.add(openAIKey)
        ChattyAIService chattyAIService = openAiServiceMap.computeIfAbsent(openAIKey,
            k -> new ChattyAIService(openAIKey, Duration.ofSeconds(openAIConfig.timeoutSeconds ?: 10), openAIConfig.baseUrl ?: ChattyAIService.BASE_URL))
        refreshKeys.add(chattyAIService)
      }
      enabledKeys = refreshKeys

      // 移除 map 中已经不使用的 openai 实例
      openAiServiceMap.keySet().stream().filter { !currentKeys.contains(it) } collect(Collectors.toList()) forEach { openAiServiceMap.remove(it) }

    }
  }
}
