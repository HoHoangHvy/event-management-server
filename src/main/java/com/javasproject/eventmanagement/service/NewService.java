package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.NewCreationRequest;
import com.javasproject.eventmanagement.dto.response.NewResponse;
import com.javasproject.eventmanagement.entity.New;
import com.javasproject.eventmanagement.exception.AppException;
import com.javasproject.eventmanagement.exception.ErrorCode;
import com.javasproject.eventmanagement.mapper.NewMapper;
import com.javasproject.eventmanagement.repository.NewRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class NewService {
    NewRepository newsRepository;
    NewMapper newsMapper;

    public NewResponse createNew(NewCreationRequest request) {
        New news = new New();
        news.setName(request.getName());
        news.setType(request.getType());
        news.setContent(request.getContent());
        news.setDeleted(false);
        news.setEmployee(userService.getCurrentUser().getEmployee());

        New savedNew = newsRepository.save(news);
        return newsMapper.toNewResponse(savedNew);
    }
    UserService userService;
    public NewResponse updateNew(String newsId, NewCreationRequest request) {
        New news = newsRepository.findById(newsId).orElseThrow(() -> new AppException(ErrorCode.NEWS_NOT_FOUND));

        if (request.getName() != null && !request.getName().isEmpty()) {
            news.setName(request.getName());
        }
        if (request.getType() != null && !request.getType().isEmpty()) {
            news.setType(request.getType());
        }
        if (request.getContent() != null && !request.getContent().isEmpty()) {
            news.setContent(request.getContent());
        }

        return newsMapper.toNewResponse(newsRepository.save(news));
    }

    public void deleteNew(String newsId) {
        New news = newsRepository.findById(newsId).orElseThrow(() -> new AppException(ErrorCode.NEWS_NOT_FOUND));
        news.setDeleted(true);
        newsRepository.save(news);
    }

    public List<NewResponse> getAllNew() {
        return newsRepository.findAllActive().stream().map(newsMapper::toNewResponse).collect(Collectors.toList());
    }

    public NewResponse getNewById(String id) {
        return newsRepository.findById(id).map(newsMapper::toNewResponse).orElseThrow(() -> new AppException(ErrorCode.NEWS_NOT_FOUND));
    }
}
