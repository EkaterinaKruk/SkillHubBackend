package by.bsuir.skillhub.controllers;

import by.bsuir.skillhub.dto.*;
import by.bsuir.skillhub.repo.UsersRepository;
import by.bsuir.skillhub.entity.UserProgress;
import by.bsuir.skillhub.repo.UserProgressRepository;
import by.bsuir.skillhub.services.CoursesService;
import by.bsuir.skillhub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('user','admin','teacher')")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CoursesService coursesService;
    private final UsersRepository usersRepository;
    private final UserProgressRepository userProgressRepository;

    //Находим пользователя по ID
    @GetMapping("/get-user/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) throws Exception {
        return userService.getUser(userId);
    }

    @GetMapping("/get-user-progress/{userId}")
    public List<UserProgress> getUserProgress(@PathVariable Long userId) throws Exception {
        return userProgressRepository.findByUser(usersRepository.findById(userId).get());
    }

    @GetMapping("/get-continue-courses/{userId}")
    public List<ContinueCourseDto> getContinueCourses(@PathVariable Long userId) throws Exception {
        return coursesService.getContinueCourses(usersRepository.findById(userId).get());
    }

    @GetMapping("/get-all-courses-for-user/{userId}")
    public List<AllCoursesDto> getAllCoursesForUser(@PathVariable Long userId) throws Exception {
        return coursesService.getAllCoursesForUser(usersRepository.findById(userId).get());
    }

    @PostMapping("/find-courses-by-name-for-user")
    public List<AllCoursesDto> findCoursesByNameForUser(@RequestBody FindCourseByNameForUserDto requestBody) throws Exception {
        return coursesService.findCoursesByNameForUser(
                usersRepository.findById(requestBody.getUserId()).get(),
                requestBody.getCourseName());
    }

    @PostMapping("/request-access")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpStatus requestAccess(@RequestBody RequestAccessDto requestBody) throws Exception {
        return coursesService.requestAccess(requestBody);
    }

}
