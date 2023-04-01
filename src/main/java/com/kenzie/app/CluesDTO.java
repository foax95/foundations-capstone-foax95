package com.kenzie.app;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CluesDTO{
    @JsonProperty("clues")
    public List<Clue> getClues() {
        return this.clues; }
    public void setClues(List<Clue> clues) {
        this.clues = clues; }
    private List<Clue> clues;

    @Override
    public String toString() {
        return "CluesDTO{" +
                "clues=" + clues + "\n" +
                '}';
    }
}

class Category{
    @JsonProperty("id")
    public int getId() {
        return this.id; }
    public void setId(int id) {
        this.id = id; }
    private int id;
    @JsonProperty("title")
    public String getTitle() {
        return this.title; }
    public void setTitle(String title) {
        this.title = title; }
    private String title;
    @JsonProperty("canon")
    public boolean getCanon() {
        return this.canon; }
    public void setCanon(boolean canon) {
        this.canon = canon; }
    private boolean canon;

    @Override
    public String toString() {
        return "Category:" + "\n" +
                "  id=" + id + "\n" +
             ", title='" + title + "\n" +
                ", canon=" + canon + "\n" +
                "\n" +'}';
    }
}

class Clue{
    private int id;
    private String answer;
    private String question;
    private int value;
    private int categoryId;
    private int gameId;
    private Game game;
    private int invalidCount;
    private Category category;
    private boolean canon;
    @JsonProperty("id")
    public int getId() {
        return this.id; }
    public void setId(int id) {
        this.id = id; }
    @JsonProperty("answer")
    public String getAnswer() {
        return this.answer; }
    public void setAnswer(String answer) {
        this.answer = answer; }
    @JsonProperty("question")
    public String getQuestion() {
        return this.question; }
    public void setQuestion(String question) {
        this.question = question; }
    @JsonProperty("value")
    public int getValue() {
        return this.value; }
    public void setValue(int value) {
        this.value = value; }
    @JsonProperty("categoryId")
    public int getCategoryId() {
        return this.categoryId; }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId; }
    @JsonProperty("gameId")
    public int getGameId() {
        return this.gameId; }
    public void setGameId(int gameId) {
        this.gameId = gameId; }

    @JsonProperty("invalidCount")
    public int getInvalidCount() {
        return this.invalidCount; }
    public void setInvalidCount(int invalidCount) {
        this.invalidCount = invalidCount; }
    @JsonProperty("category")
    public Category getCategory() {
        return this.category; }
    public void setCategory(Category category) {
        this.category = category; }
    @JsonProperty("game")
    public Game getGame() {
        return this.game; }
    public void setGame(Game game) {
        this.game = game; }

    @JsonProperty("canon")
    public boolean getCanon() {
        return this.canon; }
    public void setCanon(boolean canon) {
        this.canon = canon; }


    @Override
    public String toString() {
        return "Clue:" + "\n" +
                "  id=" + id + "\n" +
                ", question='" + question + "\n" +
                ", answer='" + answer + "\n" +
                ", value=" + value + "\n" +
                ", categoryId=" + categoryId + "\n" +
                ", gameId=" + gameId + "\n" +
                ", invalidCount=" + invalidCount + "\n" +
                ", category=" + category + "\n" +
                ", game=" + game + "\n" +
                ", canon=" + canon + "\n" + "\n"
                ;
    }
}
class Game{
    @JsonProperty("aired")
    public String getAired() {
        return this.aired; }
    public void setAired(String aired) {
        this.aired = aired; }
    private String aired;
    @JsonProperty("canon")
    public boolean getCanon() {
        return this.canon; }
    public void setCanon(boolean canon) {
        this.canon = canon; }
    private boolean canon;

    @Override
    public String toString() {
        return "Game{" +
                "aired='" + aired + '\'' +
                ", canon=" + canon +
                '}';
    }
}



