package com.example.Users.Redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Users.Config.CacheConfig;

@Service("RedisService")
public class RedisService {

	@Autowired
	public CacheConfig cacheConfig;

	public Boolean isKeyExist(String key, Object hashKey) {
		System.err.println("nfasiufasufnasfnasiofns");
		return cacheConfig.redisTemplate().opsForHash().hasKey(key, hashKey);
	}

	public void addInCache(String key, Object hashKey, Object value) {
		cacheConfig.redisTemplate().opsForHash().put(key, hashKey, value);
	}

	public Boolean addInCacheIfAbsent(String key, Object hashKey, Object value) {
		return cacheConfig.redisTemplate().opsForHash().putIfAbsent(key, hashKey, value);
	}

	public Object getFromCache(String key, Object hashKey) {
		return cacheConfig.redisTemplate().opsForHash().get(key, hashKey);
	}

}
