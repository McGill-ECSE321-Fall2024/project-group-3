package ca.mcgill.ecse321.GameOn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.stream.Collectors;

import ca.mcgill.ecse321.GameOn.dto.ReviewDto;
import ca.mcgill.ecse321.GameOn.service.ReviewService;
import ca.mcgill.ecse321.GameOn.model.Review;

/**
 * This class represents the Review Controller, which handles the HTTP
 * requests related to reviews.
 * It provides endpoints for posting, retrieving and liking reviews,
 * as well as adding replies to reviews.
 *
 * @author Mathieu Allaire
 */
@CrossOrigin(origins = "http://localhost:8087")
@RestController
public class ReviewController{
    @Autowired
    private ReviewService reviewService;

    /**
     * Posts a new review.
     *
     * @param reviewDto The review data transfer object.
     * @return The created review DTO.
     * @author Mathieu Allaire
     */
    @PostMapping("/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewDto postReview(@RequestBody ReviewDto reviewDto) {
        Review review = reviewService.postReview(
                    reviewDto.getDescription(),
                    reviewDto.getStars(),
                    reviewDto.getLikes(),
                    reviewDto.getDislikes(),
                    reviewDto.getCustomerEmail(),
                    reviewDto.getManagerEmail()
        );
        return new ReviewDto(review);

    }


    /**
     * Retrieves all reviews for a specified game.
     *
     * @param gameName The name of the game.
     * @return A list of review DTOs for the game.
     * @author Mathieu Allaire
     */
    @GetMapping("/game/{gameName}/reviews")
    public ResponseEntity<?> getAllReviewsForGame(@PathVariable String gameName){
        try{
            List<Review> reviews = reviewService.getAllReviewsForGame(gameName);
            System.out.println("Serving reviews: " + reviews.size());
            System.out.println("For the game: " + gameName);
            List<ReviewDto> response = reviews.stream()
                    .map(ReviewDto::new)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Finds a review by its ID.
     *
     * @param id The review ID.
     * @return The review with the specified ID.
     * @author Mathieu Allaire
     */
    @GetMapping("/reviews/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReviewDto findReviewById(@PathVariable int id) {
        Review review = reviewService.findReviewById(id);
        return new ReviewDto(review);
    }

    /**
     * Adds a reply to a review.
     *
     * @param id The review ID.
     * @param reply The reply content.
     * @return The updated review DTO with the new reply.
     * @author Mathieu Allaire
     */
    @PostMapping("/reviews/{id}/reply")
    public ResponseEntity<?> addReply(@PathVariable int id, @RequestBody String reply) {
        try {
            Review review = reviewService.addReply(id, reply);
            ReviewDto response = new ReviewDto(review);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Adds a like to a review.
     *
     * @param id The review ID.
     * @return The updated review with the incremented like count.
     * @author Mathieu Allaire
     */
    @PostMapping("/reviews/{id}/addLike")
    public ResponseEntity<?> likeReview(@PathVariable int id) {
        try {
            Review review = reviewService.likeReview(id);
            ReviewDto response = new ReviewDto(review);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/reviews/{id}/addDislike")
    public ResponseEntity<?> dislikeReview(@PathVariable int id) {
        try {
            Review review = reviewService.dislikeReview(id);
            ReviewDto response = new ReviewDto(review);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/reviews/{id}/updateLikes")
    public ResponseEntity<?> updateLikes(@PathVariable int id, @RequestBody int likes) {
        try {
            Review review = reviewService.setLikesReview(id, likes);
            ReviewDto response = new ReviewDto(review);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/reviews/{id}/updateDislikes")
    public ResponseEntity<?> updateDislikes(@PathVariable int id, @RequestBody int dislikes) {
        try {
            Review review = reviewService.setDislikesReview(id, dislikes);
            ReviewDto response = new ReviewDto(review);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/reviews/{id}/updateStars")
    public ResponseEntity<?> updateStars(@PathVariable int id, @RequestBody int stars) {
        try {
            Review review = reviewService.setStarsReview(id, stars);
            ReviewDto response = new ReviewDto(review);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
