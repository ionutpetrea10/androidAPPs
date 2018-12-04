package com.nordwest.university_app;

public class ReviewHolder {
    String reviewID;
    String bookISBN;
    String reviewAuthor;
    String reviewText;

    public ReviewHolder() {
    }

    public ReviewHolder(String reviewID, String bookISBN, String reviewAuthor, String reviewText) {
        this.reviewID = reviewID;
        this.bookISBN = bookISBN;
        this.reviewAuthor = reviewAuthor;
        this.reviewText = reviewText;
    }

    public String getReviewID() {
        return reviewID;
    }

    public void setReviewID(String reviewID) {
        this.reviewID = reviewID;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public String getReviewAuthor() {
        return reviewAuthor;
    }

    public void setReviewAuthor(String reviewAuthor) {
        this.reviewAuthor = reviewAuthor;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}
