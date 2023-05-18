package org.jsp.irctc.service;

import java.util.Collection;
import java.util.List;

import org.jsp.irctc.dao.TrainDao;
import org.jsp.irctc.dto.Train;
import org.jsp.irctc.helper.ResponseStructure;
import org.jsp.irctc.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class TrainService {
    @Autowired
    private TrainDao trainDao;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserDetailsService userDetailsService;

    public ResponseStructure<Train> addTrain(Train train, String token) {
        // Verify token and check if admin
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtUtil.extractUsername(token));
        
        if (jwtUtil.validateToken(token, userDetails) && isAdmin(userDetails)) {
            Train savedTrain = trainDao.save(train);
            ResponseStructure<Train> response = new ResponseStructure<>();
            response.setMessage("Train added successfully");
            response.setStatusCode(HttpStatus.CREATED.value());
            response.setData(savedTrain);
            return response;
        } else {
            ResponseStructure<Train> response = new ResponseStructure<>();
            response.setMessage("Unauthorized access. Please provide valid credentials.");
            response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
            response.setData(null);
            return response;
        }
    }

    private boolean isAdmin(UserDetails userDetails) {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                return true;
            }
        }
        return false;
    }

    public ResponseStructure<Train> fetchById(Long id, String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtUtil.extractUsername(token));
        
        if (jwtUtil.validateToken(token, userDetails)) {
            Train train = trainDao.findById(id).orElse(null);
            if (train != null) {
                ResponseStructure<Train> response = new ResponseStructure<>();
                response.setMessage("Train found");
                response.setStatusCode(HttpStatus.OK.value());
                response.setData(train);
                return response;
            }
        }
        ResponseStructure<Train> response = new ResponseStructure<>();
        response.setMessage("Train not found");
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setData(null);
        return response;
    }

    public ResponseStructure<List<Train>> fetchAll(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtUtil.extractUsername(token));
        
        if (jwtUtil.validateToken(token, userDetails)) {
            List<Train> trains = trainDao.findAll();
            if (!trains.isEmpty()) {
                ResponseStructure<List<Train>> response = new ResponseStructure<>();
                response.setMessage("Trains found");
                response.setStatusCode(HttpStatus.OK.value());
                response.setData(trains);
                return response;
            }
        }
        ResponseStructure<List<Train>> response = new ResponseStructure<>();
        response.setMessage("No trains found");
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setData(null);
        return response;
    }

    // Add more methods as needed
}
