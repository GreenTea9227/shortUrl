package yohan.practice.shorturl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yohan.practice.shorturl.entity.ShortUrl;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<ShortUrl,String> {
   Optional<ShortUrl> findByOriginalUrl(String original);
   Optional<ShortUrl> findByShortedUrl(String shortUrl);
}
