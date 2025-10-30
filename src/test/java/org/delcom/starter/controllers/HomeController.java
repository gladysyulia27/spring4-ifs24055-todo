package org.delcom.starter.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    private final HomeController controller = new HomeController();

    // ============================
    // ⿡ Tes Hello Endpoint
    // ============================
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

    // ============================
    // ⿢ Tes getProgramStudi()
    // ============================
    @Test
    @DisplayName("getProgramStudi mengembalikan nama program studi yang sesuai")
    void getProgramStudi_ShouldReturnCorrectProgram() throws Exception {
        var method = HomeController.class.getDeclaredMethod("getProgramStudi", String.class);
        method.setAccessible(true);

        assertEquals("Sarjana Informatika", method.invoke(controller, "11S"));
        assertEquals("Sarjana Sistem Informasi", method.invoke(controller, "12S"));
        assertEquals("Sarjana Teknik Elektro", method.invoke(controller, "14S"));
        assertEquals("Sarjana Manajemen Rekayasa", method.invoke(controller, "21S"));
        assertEquals("Sarjana Teknik Metalurgi", method.invoke(controller, "22S"));
        assertEquals("Sarjana Teknik Bioproses", method.invoke(controller, "31S"));
        assertEquals("Diploma 4 Teknologi Rekasaya Perangkat Lunak", method.invoke(controller, "114"));
        assertEquals("Diploma 3 Teknologi Informasi", method.invoke(controller, "113"));
        assertEquals("Diploma 3 Teknologi Komputer", method.invoke(controller, "133"));
        assertEquals("Program Studi Tidak Dikenal", method.invoke(controller, "999"));
    }

    // ============================
    // ⿣ Tes Informasi NIM
    // ============================
    @Test
    @DisplayName("Mengembalikan informasi NIM dengan benar untuk Sarjana Informatika")
    void informasiNim_ShouldReturnFormattedInfoS1IF() {
        String nim = "11S24051";
        String result = controller.informasiNim(nim);
        assertTrue(result.contains("Sarjana Informatika"));
        assertTrue(result.contains("2024"));
        assertTrue(result.contains("Urutan: 51"));
    }

    @Test
    @DisplayName("Mengembalikan informasi NIM dengan benar untuk D4 TRPL")
    void informasiNim_ShouldReturnFormattedInfoD4TRPL() {
        String nim = "11424051";
        String result = controller.informasiNim(nim);
        assertTrue(result.contains("Diploma 4 Teknologi Rekasaya Perangkat Lunak"));
        assertTrue(result.contains("2024"));
        assertTrue(result.contains("Urutan: 51"));
    }

    @Test
    @DisplayName("Mengembalikan informasi NIM untuk program studi yang tidak dikenal")
    void informasiNim_ShouldReturnUnknownProgram() {
        String nim = "99924051";
        String result = controller.informasiNim(nim);
        assertTrue(result.contains("Program Studi Tidak Dikenal"));
    }

    // ============================
    // ⿤ Tes Perolehan Nilai
    // ============================
    @Test
    @DisplayName("Perolehan Nilai menampilkan hasil decoding Base64 dengan benar")
    void perolehanNilai_ShouldReturnDecodedValue() {
        String text = "Nilai Akhir: 90";
        String encoded = Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));

        String result = controller.perolehanNilai(encoded);

        assertTrue(result.contains("Perolehan Nilai: Nilai Akhir: 90"));
    }

    // ============================
    // ⿥ Tes Perbedaan L dan Kebalikannya (String)
    // ============================
    @Test
    @DisplayName("Perbedaan L mengembalikan teks asli, kebalikan, dan perbedaan huruf dengan benar")
    void perbedaanL_ShouldReturnTextReverseAndDiff() {
        String text = "abcd";
        String encoded = Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));

        String result = controller.perbedaanL(encoded);

        assertTrue(result.contains("Teks Asli: abcd"));
        assertTrue(result.contains("Kebalikannya: dcba"));
        assertTrue(result.contains("Perbedaannya"));
    }

    @Test
    @DisplayName("Perbedaan L menangani palindrome dengan benar")
    void perbedaanL_ShouldHandlePalindrome() {
        String text = "aba";
        String encoded = Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));

        String result = controller.perbedaanL(encoded);

        assertTrue(result.contains("Teks Asli"));
        assertTrue(result.contains("Kebalikannya"));
        assertTrue(result.contains("Perbedaannya"));
    }

    // ============================
    // ⿦ Tes Paling Ter
    // ============================
    @Test
    @DisplayName("PalingTer menampilkan kata terpendek dan terpanjang dengan benar")
    void palingTer_ShouldReturnShortestAndLongestWords() {
        String text = "Saya belajar Spring Boot";
        String encoded = Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));

        String result = controller.palingTer(encoded);

        assertTrue(result.contains("Kalimat"));
        assertTrue(result.contains("Paling Pendek"));
        assertTrue(result.contains("Paling Panjang"));
        assertTrue(result.contains("Spring"));
    }

    @Test
    @DisplayName("PalingTer menangani satu kata dengan benar")
    void palingTer_ShouldHandleSingleWord() {
        String text = "Informatika";
        String encoded = Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));

        String result = controller.palingTer(encoded);

        assertTrue(result.contains("Informatika"));
        assertTrue(result.contains("Paling Pendek: Informatika"));
        assertTrue(result.contains("Paling Panjang: Informatika"));
    }
}
