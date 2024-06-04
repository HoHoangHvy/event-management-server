package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.NewCreationRequest;
import com.javasproject.eventmanagement.dto.request.NotificationCreationRequest;
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
    NotificationService notiService;
    EmployeeService employeeService;
    public NewResponse createNew(NewCreationRequest request) {
        New news = new New();
        news.setName(request.getName());
        news.setType(request.getType());
        news.setContent(request.getContent());
        news.setDeleted(false);
        news.setEmployee(userService.getCurrentUser().getEmployee());
        New savedNews = newsRepository.save(news);
        employeeService.getAllEmployee().forEach(employee -> {
            if(employee.getStatus().equals("Working") && userService.getCurrentUser().getEmployee().getId() != employee.getId()) {
                NotificationCreationRequest notiRequest = new NotificationCreationRequest();
                notiRequest.setName("You have new news: " + savedNews.getName());
                notiRequest.setContent(savedNews.getContent());
                notiRequest.setParentId(savedNews.getId());
                notiRequest.setType("Info");
                notiRequest.setParentType("News");
                notiRequest.setEmployeeId(employee.getId());
                notiService.createNotification(notiRequest);
            }
        });

        return newsMapper.toNewResponse(savedNews);
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
        return newsRepository.findAllByDeletedFalse().stream().map(newsMapper::toNewResponse).collect(Collectors.toList());
    }

    public NewResponse getNewById(String id) {
        return newsRepository.findById(id).map(newsMapper::toNewResponse).orElseThrow(() -> new AppException(ErrorCode.NEWS_NOT_FOUND));
    }
}
