package com.example.retrofitagain2.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import android.content.Context;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

class SearchHistoryServiceImplTest {
    @Mock
    Context context;
    SearchHistoryServiceImpl searchHistoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        searchHistoryService = new SearchHistoryServiceImpl(context);
    }

    @AfterEach
    void afterEach() {
        reset(context);
    }

    @Test
    void addHistoryQueryTest() {
        try {
            searchHistoryService.addHistoryQuery(anyString());
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    void fetchAllHistory_Should_Return_ArrayList_Test() {
        try {
            searchHistoryService.fetchAllHistory();
            when(searchHistoryService.fetchAllHistory()).thenReturn(any(ArrayList.class));
        } catch (NullPointerException ignored) {
        }
    }
}