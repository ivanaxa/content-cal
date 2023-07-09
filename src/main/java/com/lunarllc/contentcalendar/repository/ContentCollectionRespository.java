package com.lunarllc.contentcalendar.repository;

import com.lunarllc.contentcalendar.model.Content;
import com.lunarllc.contentcalendar.model.Status;
import com.lunarllc.contentcalendar.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRespository {

    private final List<Content> list = new ArrayList<>();
    public ContentCollectionRespository(){

    }

    public List<Content> findAll() {
        return list;
    }

    public Optional<Content> findById(Integer id){
        return list.stream().filter(c -> c.id().equals(id)).findFirst();
    }

    @PostConstruct
    private void init() {
        Content content = new Content(1,
                "My First Blog Post",
                "My first blog post",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                "");

        list.add(content);

    }

}
