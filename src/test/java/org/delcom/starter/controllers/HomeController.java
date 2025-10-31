package org.delcom.starter.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    private final HomeController controller = new HomeController();

    @Test
    @DisplayName("Mengembalikan pesan sambutan default dengan benar")
    void hello_ShouldReturnWelcomeMessage() {
        String result = controller.hello();
        assertEquals("Hay Abdullah, selamat datang di pengembangan aplikasi dengan Spring Boot!", result);
    }

    @Test
    @DisplayName("Mengembalikan sapaan personal yang benar")
    void sayHello_ShouldReturnPersonalGreeting() {
        String result = controller.sayHello("Gladys");
        assertEquals("Hello, Gladys!", result);
    }

    // ======================
    // 1️⃣ Informasi NIM
    // ======================
    @Test
    @DisplayName("Mengembalikan informasi NIM dengan benar untuk S1 Informatika")
    void informasiNim_ShouldReturnFormattedInfoS1IF() {
        // Arrange
        String nim = "11S24055";

        // Act
        String result = controller.informasiNim(nim);

        // Assert
        assertTrue(result.contains("Sarjana Informatika"));
        assertTrue(result.contains("2024"));
        assertTrue(result.contains("55"));
    }

    @Test
    @DisplayName("Mengembalikan informasi NIM dengan benar untuk S1 Sistem Informasi")
    void informasiNim_ShouldReturnFormattedInfoS1SI() {
        // Arrange
        String nim = "12S24055";

        // Act
        String result = controller.informasiNim(nim);

        // Assert
        assertTrue(result.contains("Sarjana Sistem Informasi"));
        assertTrue(result.contains("2024"));
        assertTrue(result.contains("55"));
    }

    @Test
    @DisplayName("Mengembalikan informasi NIM dengan benar untuk S1 Teknik Elektro")
    void informasiNim_ShouldReturnFormattedInfoS1TE() {
        // Arrange
        String nim = "14S24055";

        // Act
        String result = controller.informasiNim(nim);

        // Assert
        assertTrue(result.contains("Sarjana Teknik Elektro"));
        assertTrue(result.contains("2024"));
        assertTrue(result.contains("55"));
    }

    @Test
    @DisplayName("Mengembalikan informasi NIM dengan benar untuk S1 Sarjana Manajemen Rekayasa")
    void informasiNim_ShouldReturnFormattedInfoS1MR() {
        // Arrange
        String nim = "21S24055";

        // Act
        String result = controller.informasiNim(nim);

        // Assert
        assertTrue(result.contains("Sarjana Manajemen Rekayasa"));
        assertTrue(result.contains("2024"));
        assertTrue(result.contains("55"));
    }

    @Test
    @DisplayName("Mengembalikan informasi NIM dengan benar untuk S1 Sarjana Teknik Metalurgi")
    void informasiNim_ShouldReturnFormattedInfoS1TM() {
        // Arrange
        String nim = "22S24055";

        // Act
        String result = controller.informasiNim(nim);

        // Assert
        assertTrue(result.contains("Sarjana Teknik Metalurgi"));
        assertTrue(result.contains("2024"));
        assertTrue(result.contains("55"));
    }

    @Test
    @DisplayName("Mengembalikan informasi NIM dengan benar untuk S1 Sarjana Teknik Bioproses")
    void informasiNim_ShouldReturnFormattedInfoS1TB() {
        // Arrange
        String nim = "31S24055";

        // Act
        String result = controller.informasiNim(nim);

        // Assert
        assertTrue(result.contains("Sarjana Teknik Bioproses"));
        assertTrue(result.contains("2024"));
        assertTrue(result.contains("55"));
    }

    @Test
    @DisplayName("Mengembalikan informasi NIM dengan benar untuk D4 Diploma Teknologi Rekasaya Perangkat Lunak")
    void informasiNim_ShouldReturnFormattedInfoD4TRPL() {
        // Arrange
        String nim = "11424055";

        // Act
        String result = controller.informasiNim(nim);

        // Assert
        assertTrue(result.contains("Diploma 4 Teknologi Rekasaya Perangkat Lunak"));
        assertTrue(result.contains("2024"));
        assertTrue(result.contains("55"));
    }

    @Test
    @DisplayName("Mengembalikan informasi NIM dengan benar untuk D3 Diploma Teknologi Informasi")
    void informasiNim_ShouldReturnFormattedInfoD3TI() {
        // Arrange
        String nim = "11324055";

        // Act
        String result = controller.informasiNim(nim);

        // Assert
        assertTrue(result.contains("Diploma 3 Teknologi Informasi"));
        assertTrue(result.contains("2024"));
        assertTrue(result.contains("55"));
    }

    @Test
    @DisplayName("Mengembalikan informasi NIM dengan benar untuk D3 Diploma Teknologi Komputer")
    void informasiNim_ShouldReturnFormattedInfoD3TK() {
        // Arrange
        String nim = "13324055";

        // Act
        String result = controller.informasiNim(nim);

        // Assert
        assertTrue(result.contains("Diploma 3 Teknologi Komputer"));
        assertTrue(result.contains("2024"));
        assertTrue(result.contains("55"));
    }

    @Test
    @DisplayName("Mengembalikan Program Studi Tidak Dikenal jika prefix tidak cocok")
    void informasiNim_ShouldReturnUnknownProgram() {
        String nim = "90x24055";
        String result = controller.informasiNim(nim);
        assertTrue(result.contains("Program Studi Tidak Dikenal"));
    }

    // ======================
    // 2️⃣ Perolehan Nilai
    // ======================
    @Test
    @DisplayName("Mendekode base64 dan mengembalikan nilai dengan benar")
    void perolehanNilai_ShouldDecodeBase64Correctly() {
        String nilaiAsli = "A=90 B=80 C=70";
        String base64 = Base64.getEncoder().encodeToString(nilaiAsli.getBytes(StandardCharsets.UTF_8));

        String result = controller.perolehanNilai(base64);

        assertTrue(result.contains("A=90"));
        assertTrue(result.contains("Perolehan Nilai"));
    }

    // ================================
    // 3️⃣ Perbedaan L dan Kebalikannya
    // ================================
    @Test
    @DisplayName("Mengembalikan teks asli, kebalikannya, dan perbedaan hurufnya")
    void perbedaanL_ShouldReturnDifference() {
        String input = "abc";
        String base64 = Base64.getEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_8));

        String result = controller.perbedaanL(base64);

        assertTrue(result.contains("Teks Asli"));
        assertTrue(result.contains("Kebalikannya"));
        assertTrue(result.contains("Perbedaannya"));
        assertTrue(result.contains("a")); // salah satu huruf berbeda
    }

    @Test
    @DisplayName("Mengembalikan perbedaan kosong jika palindrome")
    void perbedaanL_ShouldReturnNoDifferenceIfPalindrome() {
        String input = "aba";
        String base64 = Base64.getEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_8));

        String result = controller.perbedaanL(base64);

        assertTrue(result.contains("Teks Asli"));
        assertTrue(result.contains("Kebalikannya"));
        assertTrue(result.contains("Perbedaannya"));
    }

    // ==============
    // 4️⃣ Paling Ter
    // ==============
    @Test
    @DisplayName("Menemukan kata terpendek dan terpanjang dengan benar")
    void palingTer_ShouldFindShortestAndLongestWords() {
        String kalimat = "Saya sedang belajar spring boot oop";
        String base64 = Base64.getEncoder().encodeToString(kalimat.getBytes(StandardCharsets.UTF_8));

        String result = controller.palingTer(base64);

        assertTrue(result.contains("Kalimat"));
        assertTrue(result.contains("Paling Pendek"));
        assertTrue(result.contains("Paling Panjang"));
        assertTrue(result.contains("belajar")); // terpanjang yang benar
        assertTrue(result.contains("oop")); // terpendek yang benar
    }

}