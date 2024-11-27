package model;

public class Comments {
    private static int id = 1;
    private final Integer commentId;
    private String comment;
    private Integer userId;
    private Double ratingUsers;

    public Comments(String comment, Integer userId, Double ratingUsers) {
        this.commentId = id;
        Comments.id += 1;
        this.comment = comment;
        this.userId = userId;
        this.ratingUsers = Math.max(0, Math.min(5, ratingUsers));
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public Double getRatingUsers() {
        return ratingUsers;
    }

    public void setRatingUsers(Double ratingUsers) {
        this.ratingUsers = ratingUsers;
    }
}
