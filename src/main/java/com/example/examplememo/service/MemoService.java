package com.example.examplememo.service;

import com.example.examplememo.dto.*;
import com.example.examplememo.entity.Memo;
import com.example.examplememo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;

    @Transactional
    public CreateMemoResponse save(CreateMemoRequest request) {
        Memo memo = new Memo(request.getText());
        Memo saved = memoRepository.save(memo);
        return new CreateMemoResponse(saved.getMemoId(), saved.getText(), saved.getCreatedAt(), saved.getModifiedAt());
    }

    @Transactional(readOnly = true)
    public List<GetMemoResponse> findAll() {
        List<Memo> memos = memoRepository.findAll();
        List<GetMemoResponse> dtos = new ArrayList<>();
        for (Memo memo : memos) {
            GetMemoResponse dto = new GetMemoResponse(memo.getMemoId(), memo.getText(), memo.getCreatedAt(), memo.getModifiedAt());
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public GetMemoResponse findOne(Long memoId) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new IllegalArgumentException("Memo with id " + memoId + " not found")
        );
        return new GetMemoResponse(memo.getMemoId(), memo.getText(), memo.getCreatedAt(), memo.getModifiedAt());
    }

    @Transactional
    public UpdateMemoResponse update(Long memoId, UpdateMemoRequest request) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new IllegalArgumentException("Memo with id " + memoId + " not found")
        );
        memo.update(request.getText());
        return new UpdateMemoResponse(memo.getMemoId(), memo.getText(), memo.getCreatedAt(), memo.getModifiedAt());
    }

    public void delete(Long memoId) {
        boolean existence = memoRepository.existsById(memoId);
        if (!existence) {
            throw new IllegalArgumentException("Memo with id " + memoId + " not found");
        }
        memoRepository.deleteById(memoId);
    }
}
