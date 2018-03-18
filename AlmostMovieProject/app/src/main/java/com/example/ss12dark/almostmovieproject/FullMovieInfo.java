package com.example.ss12dark.almostmovieproject;



public class FullMovieInfo extends Movie {
    private float vote_average;
    private String release_date;
    private int budget;
    private int runtime;

    public FullMovieInfo(float vote_average, String release_date, int budget, int runtime) {
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.budget = budget;
        this.runtime = runtime;
    }

    public FullMovieInfo(int id, String subject, String body, String url, float vote_average, String release_date, int budget, int runtime) {
        super(id, subject, body, url);
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.budget = budget;
        this.runtime = runtime;
    }

    public FullMovieInfo(String subject, String body, String url, float vote_average, String release_date, int budget, int runtime) {
        super(subject, body, url);
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.budget = budget;
        this.runtime = runtime;
    }

    public FullMovieInfo(String subject, String body, float vote_average, String release_date, int budget, int runtime) {
        super(subject, body);
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.budget = budget;
        this.runtime = runtime;
    }

    public FullMovieInfo(String subject, float vote_average, String release_date, int budget, int runtime) {
        super(subject);
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.budget = budget;
        this.runtime = runtime;
    }

    public FullMovieInfo(int id, String subject, String body, String url, int No, float vote_average, String release_date, int budget, int runtime) {
        super(id, subject, body, url, No);
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.budget = budget;
        this.runtime = runtime;
    }

    public FullMovieInfo(String subject, String body, String url, int No, float vote_average, String release_date, int budget, int runtime) {
        super(subject, body, url, No);
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.budget = budget;
        this.runtime = runtime;
    }

    public FullMovieInfo(String subject, String body, int No, float vote_average, String release_date, int budget, int runtime) {
        super(subject, body, No);
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.budget = budget;
        this.runtime = runtime;
    }

    public FullMovieInfo(String subject, int No, float vote_average, String release_date, int budget, int runtime) {
        super(subject, No);
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.budget = budget;
        this.runtime = runtime;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

}
