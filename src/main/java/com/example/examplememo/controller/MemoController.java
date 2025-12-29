package com.example.examplememo.controller;

import com.example.examplememo.dto.*;
import com.example.examplememo.entity.Memo;
import com.example.examplememo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {
    private final MemoService memoService;

    @PostMapping("/memos")
    public ResponseEntity<CreateMemoResponse> create(@RequestBody CreateMemoRequest request) {
        CreateMemoResponse response = memoService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/memos")
    public ResponseEntity<List<GetMemoResponse>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(memoService.findAll());
    }

    @GetMapping("/memos/{memoId}")
    public ResponseEntity<GetMemoResponse> findOne(@PathVariable Long memoId) {
        return ResponseEntity.status(HttpStatus.OK).body(memoService.findOne(memoId));
    }

    @PutMapping("/memos/{memoId}")
    public ResponseEntity<UpdateMemoResponse> update(@PathVariable Long memoId, @RequestBody UpdateMemoRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(memoService.update(memoId, request));
    }

    @DeleteMapping("/memos/{memoId}")
    public ResponseEntity<Void> delete(@PathVariable Long memoId) {
        memoService.delete(memoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
