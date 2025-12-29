package com.example.examplememo.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetMemoResponse {
    private final Long memoId;
    private final String text;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetMemoResponse(Long memoId, String text, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.memoId = memoId;
        this.text = text;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
