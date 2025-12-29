package com.example.examplememo.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateMemoResponse {
    private final Long memoId;
    private final String text;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CreateMemoResponse(Long memoId, String text, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.memoId = memoId;
        this.text = text;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
