package com.sample;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * レスポンス用クラス。
 */
@Getter
@Builder
public class Response {

    private Result result; // 処理結果

    private List<Person> persons; // データ

    @Getter
    @Builder
    public static class Result {
        private String message; // 処理結果メッセージ
        private int count; // データ件数
    }
}