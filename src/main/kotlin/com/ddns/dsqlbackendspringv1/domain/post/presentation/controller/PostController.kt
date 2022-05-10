package com.ddns.dsqlbackendspringv1.domain.post.presentation.controller

import com.ddns.dsqlbackendspringv1.domain.post.business.service.PostService
import com.ddns.dsqlbackendspringv1.domain.post.presentation.dto.response.OneFullPostResponse
import com.ddns.dsqlbackendspringv1.domain.post.presentation.dto.response.ShortPostListResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/dsql/v1/post")
class PostController(
    private val postService: PostService
) {


    @GetMapping
    fun getLatestPost(): OneFullPostResponse {
        return postService.getLatestPost()
    }

    @GetMapping("/list")
    fun getPostListOrderByDesc(@RequestParam size: Int): ShortPostListResponse {
        return postService.getPostListOrderByDesc(size)
    }


}