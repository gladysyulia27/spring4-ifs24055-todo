package org.delcom.starter.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    // ============================
    // 1️⃣ Tes Hello Endpoint
    // ============================
    @Test
    @DisplayName("Mengembalikan pesan sambutan default dengan benar")
    void hello_ShouldReturnWelcomeMessage() {
        HomeController controller = new HomeController();
        String result = controller.hello();
        assertEquals("Hay Abdullah, selamat datang di pengembangan aplikasi dengan Spring Boot!", result);
    }

    @Test
    @DisplayName("Mengembalikan sapaan personal yang benar")
    void sayHello_ShouldReturnPersonalGreeting() {
        HomeController controller = new HomeController();
        String result = controller.sayHello("Gladys");
        assertEquals("Hello, Gladys!", result);
    }

    // ============================
    // 2️⃣ Tes Informasi NIM
    // ============================
    @Test
    @DisplayName("Mengembalikan informasi NIM dengan benar untuk Sarjana Informatika")
    void informasiNim_ShouldReturnFormattedInfoS1IF() {
        HomeController controller = new HomeController();
        String nim = "11S24051";

        String result = controller.informasiNim(nim);

        assertTrue(result.contains("Sarjana Informatika"));
        assertTrue(result.contains("2024"));
        assertTrue(result.contains("Urutan: 51"));
    }

    @Test
    @DisplayName("Mengembalikan informasi NIM untuk program studi lain")
    void informasiNim_ShouldReturnCorrectProgramStudi() {
        HomeController controller = new HomeController();
        String nim = "12S23002"; // Sistem Informasi

        String result = controller.informasiNim(nim);

        assertTrue(result.contains("Sarjana Sistem Informasi"));
        assertTrue(result.contains("2023"));
        assertTrue(result.contains("Urutan: 2"));
    }

    // ============================
    // 3️⃣ Tes Perolehan Nilai
    // ============================
    @Test
    @DisplayName("Perolehan Nilai menampilkan hasil decoding Base64 dengan benar")
    void perolehanNilai_ShouldReturnDecodedValue() {
        HomeController controller = new HomeController();
        String text = "Nilai Akhir: 90";
        String encoded = Base64.getEncoder().encodeToString(text.getBytes());

        String result = controller.perolehanNilai(encoded);

        assertTrue(result.contains("Perolehan Nilai: Nilai Akhir: 90"));
    }

    // ============================
    // 4️⃣ Tes Perbedaan L dan Kebalikannya
    // ============================
    @Test
    @DisplayName("Perbedaan L menampilkan hasil perbandingan teks dengan kebalikannya")
    void perbedaanL_ShouldReturnDifference() {
        HomeController controller = new HomeController();
        String text = "ABCD";
        String encoded = Base64.getEncoder().encodeToString(text.getBytes());

        String result = controller.perbedaanL(encoded);

        assertTrue(result.contains("Teks Asli: ABCD"));
        assertTrue(result.contains("Kebalikannya: DCBA"));
        assertTrue(result.contains("Perbedaannya"));
    }

    @Test
    @DisplayName("Perbedaan L pada palindrome menampilkan perbedaan kosong")
    void perbedaanL_ShouldHandlePalindromeCorrectly() {
        HomeController controller = new HomeController();
        String text = "KAYAK"; // palindrome
        String encoded = Base64.getEncoder().encodeToString(text.getBytes());

        String result = controller.perbedaanL(encoded);

        assertTrue(result.contains("Teks Asli: KAYAK"));
        assertTrue(result.contains("Kebalikannya: KAYAK"));
    }

    // ============================
    // 5️⃣ Tes Paling Ter
    // ============================
    @Test
    @DisplayName("PalingTer menampilkan kata terpendek dan terpanjang dengan benar")
    void palingTer_ShouldReturnShortestAndLongestWords() {
        HomeController controller = new HomeController();
        String text = "Saya belajar Spring Boot";
        String encoded = Base64.getEncoder().encodeToString(text.getBytes());

        String result = controller.palingTer(encoded);

        assertTrue(result.contains("Paling Pendek"));
        assertTrue(result.contains("Paling Panjang"));
        assertTrue(result.contains("Spring"));
    }

    @Test
    @DisplayName("PalingTer menangani satu kata dengan benar")
    void palingTer_ShouldHandleSingleWord() {
        HomeController controller = new HomeController();
        String text = "Informatika";
        String encoded = Base64.getEncoder().encodeToString(text.getBytes());

        String result = controller.palingTer(encoded);

        assertTrue(result.contains("Informatika"));
        assertTrue(result.contains("Paling Pendek: Informatika"));
        assertTrue(result.contains("Paling Panjang: Informatika"));
    }
}