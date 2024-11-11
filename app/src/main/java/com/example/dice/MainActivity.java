package com.example.dice;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // Deklaracja zmiennych
    private TextView dice1TextView, dice2TextView, dice3TextView, dice4TextView, dice5TextView;
    private TextView currentRollLabel, gameResultLabel, rollCountTextView;
    private Button rollDiceButton, resetButton;
    private int totalScore = 0;
    private int rollCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicjalizacja widoków
        initializeViews();

        // Ustawienie nasłuchiwaczy zdarzeń
        setButtonListeners();
    }

    // Metoda do inicjalizacji widoków
    private void initializeViews() {
        dice1TextView = findViewById(R.id.dice1TextView);
        dice2TextView = findViewById(R.id.dice2TextView);
        dice3TextView = findViewById(R.id.dice3TextView);
        dice4TextView = findViewById(R.id.dice4TextView);
        dice5TextView = findViewById(R.id.dice5TextView);
        currentRollLabel = findViewById(R.id.currentRollLabel);
        gameResultLabel = findViewById(R.id.gameResultLabel);
        rollCountTextView = findViewById(R.id.rollCountTextView);
        rollDiceButton = findViewById(R.id.rollDiceButton);
        resetButton = findViewById(R.id.resetButton);
    }

    // Metoda do ustawiania nasłuchiwaczy zdarzeń
    private void setButtonListeners() {
        rollDiceButton.setOnClickListener(v -> rollDice());
        resetButton.setOnClickListener(v -> resetGame());
    }

    // Metoda do rzucania kośćmi
    private void rollDice() {
        // Generuj liczby
        int[] diceValues = new int[5];
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            diceValues[i] = random.nextInt(6) + 1;
        }

        // Wyświetl liczby
        displayDiceValues(diceValues);

        // Liczenie wyniku
        int rollScore = calculateScore(diceValues);
        totalScore += rollScore;
        rollCount++;

        // Wyświetlanie po losowaniu
        updateLabels(rollScore);
    }

    // Metoda do wyświetlania wartości kości
    private void displayDiceValues(int[] diceValues) {
        dice1TextView.setText(String.valueOf(diceValues[0]));
        dice2TextView.setText(String.valueOf(diceValues[1]));
        dice3TextView.setText(String.valueOf(diceValues[2]));
        dice4TextView.setText(String.valueOf(diceValues[3]));
        dice5TextView.setText(String.valueOf(diceValues[4]));
    }

    // Metoda do aktualizacji etykiet
    private void updateLabels(int rollScore) {
        currentRollLabel.setText("Wynik tego losowania: " + rollScore);
        gameResultLabel.setText("Wynik gry: " + totalScore);
        rollCountTextView.setText("Liczba rzutów: " + rollCount);
    }

    // Metoda do obliczania wyniku
    private int calculateScore(int[] diceValues) {
        Map<Integer, Integer> frequency = new HashMap<>();

        for (int value : diceValues) {
            frequency.put(value, frequency.getOrDefault(value, 0) + 1);
        }

        // Liczenie wyniku
        int score = 0;
        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            if (entry.getValue() >= 2) {
                score += entry.getKey() * entry.getValue();
            }
        }
        return score;
    }

    // Metoda do resetowania gry
    private void resetGame() {
        // Reset pola
        resetDiceValues();

        // Reset wyniki
        totalScore = 0;
        rollCount = 0;

        // Wyświetlanie
        currentRollLabel.setText("Wynik tego losowania: 0");
        gameResultLabel.setText("
