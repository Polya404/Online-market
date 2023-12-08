package com.market.back.services.impl;

import com.market.back.repositories.UserRepository;
import com.market.back.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
}
