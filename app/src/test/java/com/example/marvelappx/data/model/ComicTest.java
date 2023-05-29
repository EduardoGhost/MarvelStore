package com.example.marvelappx.data.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import static kotlin.reflect.KClasses.cast;

import androidx.appcompat.app.ActionBar;

import com.example.marvelappx.ui.ListComics;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(JUnit4.class)
public class ComicTest{

    @Test
public void Test1(){

        List<Price> prices = new ArrayList<>();
        prices.add(new Price("raro",22));

        Comic comic = new Comic("Miranha","ele voltou pra casa",
                new Image("path", ".jpg"), prices, true);

        assertEquals("Miranha", comic.getTitle());
        assertEquals("ele voltou pra casa", comic.getDescription());
        assertEquals("path.jpg", comic.getThumbnail().getPath()+comic.getThumbnail().getExtension());
        assertEquals(prices, comic.getPrices());
        assertTrue(comic.isRare());



}
}