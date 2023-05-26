import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

public class LordOfTheRingsSDK {
    private static final String BASE_URL = "https://lordoftheringsapi.com/api/v1";

    private final OkHttpClient httpClient;
    private final Gson gson;

    public LordOfTheRingsSDK() {
        httpClient = new OkHttpClient();
        gson = new GsonBuilder().create();
    }

    public List<Movie> getMovies() throws LordOfTheRingsException {
        String url = BASE_URL + "/movie";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new LordOfTheRingsException("Failed to get movies. HTTP error code: " + response.code());
            }

            String responseBody = response.body().string();
            return gson.fromJson(responseBody, MovieResponse.class).getMovies();
        } catch (IOException e) {
            throw new LordOfTheRingsException("Failed to get movies: " + e.getMessage());
        }
    }

    public Movie getMovieById(String id) throws LordOfTheRingsException {
        String url = BASE_URL + "/movie/" + id;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new LordOfTheRingsException("Failed to get movie by ID. HTTP error code: " + response.code());
            }

            String responseBody = response.body().string();
            return gson.fromJson(responseBody, Movie.class);
        } catch (IOException e) {
            throw new LordOfTheRingsException("Failed to get movie by ID: " + e.getMessage());
        }
    }

    public List<Quote> getQuotesForMovie(String movieId) throws LordOfTheRingsException {
        String url = BASE_URL + "/movie/" + movieId + "/quote";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new LordOfTheRingsException("Failed to get quotes for movie. HTTP error code: " + response.code());
            }

            String responseBody = response.body().string();
            return gson.fromJson(responseBody, QuoteResponse.class).getQuotes();
        } catch (IOException e) {
            throw new LordOfTheRingsException("Failed to get quotes for movie: " + e.getMessage());
        }
    }

    public List<Quote> getQuotes() throws LordOfTheRingsException {
        String url = BASE_URL + "/quote";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new LordOfTheRingsException("Failed to get quotes. HTTP error code: " + response.code());
            }

            String responseBody = response.body().string();
            return gson.fromJson(responseBody, QuoteResponse.class).getQuotes();
        } catch (IOException e) {
            throw new LordOfTheRingsException("Failed to get quotes: " + e.getMessage());
        }
    }

    public Quote getQuoteById(String id) throws LordOfTheRingsException {
        String url = BASE_URL + "/quote/" + id;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new LordOfTheRingsException("Failed to get quote by ID. HTTP error code: " + response.code());
            }

            String responseBody = response.body().string();
            return gson.fromJson(responseBody, Quote.class);
        } catch (IOException e) {
            throw new LordOfTheRingsException("Failed to get quote by ID: " + e.getMessage());
        }
    }
}
