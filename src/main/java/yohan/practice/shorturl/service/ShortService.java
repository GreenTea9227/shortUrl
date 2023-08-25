package yohan.practice.shorturl.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import yohan.practice.shorturl.common.EncodingService;
import yohan.practice.shorturl.common.MyLocation;
import yohan.practice.shorturl.entity.ShortUrl;
import yohan.practice.shorturl.ex.NoSuchUrlException;
import yohan.practice.shorturl.repository.UrlRepository;

import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ShortService {

    private final RedisTemplate<String,Object> redisTemplate;
    private final UrlRepository urlRepository;
    private final EncodingService base62Utils;

    public String requestShortUrl(String shortUrl) {

        if (Boolean.TRUE.equals(redisTemplate.hasKey(shortUrl))) {
            return (String) redisTemplate.opsForValue().get(shortUrl);
        }

        Optional<ShortUrl> byShortedUrl = urlRepository.findByShortedUrl(shortUrl);
        if (byShortedUrl.isPresent()) {
            ShortUrl shortResult = byShortedUrl.get();
            redisTemplate.opsForValue().set(shortResult.getShortedUrl(),shortResult.getOriginalUrl());
            return byShortedUrl.get().getOriginalUrl();
        }

        throw new NoSuchUrlException();
    }

    public String makeShortUrl(String originalUrl) {

        Optional<ShortUrl> byOriginalUrl = urlRepository.findByOriginalUrl(originalUrl);

        if (byOriginalUrl.isPresent()) {
            return byOriginalUrl.get().getShortedUrl();
        }

        Long shaEncode = base62Utils.shaEncode(originalUrl);

        String base62Encode = base62Utils.base62Encode(shaEncode);
        urlRepository.save(new ShortUrl(shaEncode,base62Encode, originalUrl));

        return MyLocation.location+"/"+base62Encode;
    }

}
