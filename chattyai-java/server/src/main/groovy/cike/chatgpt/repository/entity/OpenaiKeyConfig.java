package cike.chatgpt.repository.entity;

import java.util.Date;

public class OpenaiKeyConfig {

  private Long id;

  private String accountId;

  private String openaiKey;

  private String supportModel;

  private Integer totalUseToken;

  private Integer totalUseMoney;

  private Integer maxUseMoney;

  private Byte status;

  private Date createTime;

  private Date expiredTime;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public String getOpenaiKey() {
    return openaiKey;
  }

  public void setOpenaiKey(String openaiKey) {
    this.openaiKey = openaiKey;
  }

  public String getSupportModel() {
    return supportModel;
  }

  public void setSupportModel(String supportModel) {
    this.supportModel = supportModel;
  }

  public Integer getTotalUseToken() {
    return totalUseToken;
  }

  public void setTotalUseToken(Integer totalUseToken) {
    this.totalUseToken = totalUseToken;
  }

  public Integer getTotalUseMoney() {
    return totalUseMoney;
  }

  public void setTotalUseMoney(Integer totalUseMoney) {
    this.totalUseMoney = totalUseMoney;
  }

  public Integer getMaxUseMoney() {
    return maxUseMoney;
  }

  public void setMaxUseMoney(Integer maxUseMoney) {
    this.maxUseMoney = maxUseMoney;
  }

  public Byte getStatus() {
    return status;
  }

  public void setStatus(Byte status) {
    this.status = status;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getExpiredTime() {
    return expiredTime;
  }

  public void setExpiredTime(Date expiredTime) {
    this.expiredTime = expiredTime;
  }
}