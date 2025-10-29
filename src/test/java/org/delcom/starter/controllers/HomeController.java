package org.delcom.starter.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;
import java.lang.reflect.Method;

class HomeControllerUnitTest {
    
    // Test untuk metode hello()
    @Test
    @DisplayName("Mengembalikan pesan selamat datang yang benar")
    void hello_ShouldReturnWelcomeMessage() {
        // Arrange
        HomeController controller = new HomeController();

        // Act
        String result = controller.hello();

        // Assert
        assertEquals("Hay Abdullah, selamat datang di pengembangan aplikasi dengan Spring Boot!", result);
    }

    // Test untuk metode sayHello dengan parameter nama
    @Test
    @DisplayName("Mengembalikan pesan sapaan yang dipersonalisasi")
    void helloWithName_ShouldReturnPersonalizedGreeting() {
        // Arrange
        HomeController controller = new HomeController();

        // Act
        String result = controller.sayHello("Abdullah");

        // Assert
        assertEquals("Hello, Abdullah!", result);
    }

    @Test
    @DisplayName("Mengembalikan pesan sapaan untuk nama berbeda")
    void helloWithName_ShouldReturnPersonalizedGreetingForDifferentName() {
        // Arrange
        HomeController controller = new HomeController();

        // Act
        String result = controller.sayHello("Budi");

        // Assert
        assertEquals("Hello, Budi!", result);
    }

    // Test untuk metode informasiNim - NIM valid
    @Test
    @DisplayName("Mengembalikan informasi NIM yang valid untuk Informatika")
    void informasiNim_ShouldReturnValidNimInformationInformatika() {
        // Arrange
        HomeController controller = new HomeController();

        // Act
        Map<String, String> result = controller.informasiNim("11S21001");

        // Assert
        assertEquals("11S21001", result.get("nim"));
        assertEquals("Sarjana Informatika", result.get("programStudi"));
        assertEquals("2021", result.get("angkatan"));
        assertEquals("1", result.get("urutan"));
        assertNull(result.get("error"));
    }

    @Test
    @DisplayName("Mengembalikan informasi NIM yang valid untuk Sistem Informasi")
    void informasiNim_ShouldReturnValidNimInformationSistemInformasi() {
        // Arrange
        HomeController controller = new HomeController();

        // Act
        Map<String, String> result = controller.informasiNim("12S22015");

        // Assert
        assertEquals("12S22015", result.get("nim"));
        assertEquals("Sarjana Sistem Informasi", result.get("programStudi"));
        assertEquals("2022", result.get("angkatan"));
        assertEquals("15", result.get("urutan"));
    }

    @Test
    @DisplayName("Mengembalikan informasi NIM yang valid untuk Teknik Elektro")
    void informasiNim_ShouldReturnValidNimInformationTeknikElektro() {
        // Arrange
        HomeController controller = new HomeController();

        // Act
        Map<String, String> result = controller.informasiNim("14S23025");

        // Assert
        assertEquals("14S23025", result.get("nim"));
        assertEquals("Sarjana Teknik Elektro", result.get("programStudi"));
        assertEquals("2023", result.get("angkatan"));
        assertEquals("25", result.get("urutan"));
    }

    @Test
    @DisplayName("Mengembalikan error untuk NIM yang terlalu pendek")
    void informasiNim_ShouldReturnErrorForShortNim() {
        // Arrange
        HomeController controller = new HomeController();

        // Act
        Map<String, String> result = controller.informasiNim("11S");

        // Assert
        assertEquals("NIM harus minimal 6 karakter", result.get("error"));
        assertNull(result.get("nim"));
    }

    @Test
    @DisplayName("Mengembalikan error untuk NIM null")
    void informasiNim_ShouldReturnErrorForNullNim() {
        // Arrange
        HomeController controller = new HomeController();

        // Act
        Map<String, String> result = controller.informasiNim(null);

        // Assert
        assertEquals("NIM harus minimal 6 karakter", result.get("error"));
    }

    @Test
    @DisplayName("Mengembalikan error untuk NIM dengan format tidak valid")
    void informasiNim_ShouldReturnErrorForInvalidNimFormat() {
        // Arrange
        HomeController controller = new HomeController();

        // Act
        Map<String, String> result = controller.informasiNim("11SXX001"); // XX tidak bisa diparse ke angka

        // Assert
        assertEquals("Format NIM tidak valid", result.get("error"));
    }

    @Test
    @DisplayName("Mengembalikan Program Studi Tidak Dikenal untuk prefix invalid")
    void informasiNim_ShouldReturnUnknownProgramStudiForInvalidPrefix() {
        // Arrange
        HomeController controller = new HomeController();

        // Act
        Map<String, String> result = controller.informasiNim("99921001");

        // Assert
        assertEquals("Program Studi Tidak Dikenal", result.get("programStudi"));
    }

    // Test untuk metode perolehanNilai - data valid
    @Test
    @DisplayName("Mengembalikan statistik nilai yang benar")
    void perolehanNilai_ShouldReturnCorrectStatistics() {
        // Arrange
        HomeController controller = new HomeController();
        String base64Data = java.util.Base64.getEncoder().encodeToString("80,85,90,75,95".getBytes());

        // Act
        Map<String, Object> result = controller.perolehanNilai(base64Data);

        // Assert
        assertEquals("80,85,90,75,95", result.get("data"));
        assertEquals(5, result.get("jumlah"));
        assertEquals(425, result.get("total"));
        assertEquals("85.00", result.get("rataRata"));
        assertEquals(95, result.get("nilaiTertinggi"));
        assertEquals(75, result.get("nilaiTerendah"));
        assertNull(result.get("error"));
    }

    @Test
    @DisplayName("Mengembalikan statistik untuk satu nilai")
    void perolehanNilai_ShouldHandleSingleValue() {
        // Arrange
        HomeController controller = new HomeController();
        String base64Data = java.util.Base64.getEncoder().encodeToString("100".getBytes());

        // Act
        Map<String, Object> result = controller.perolehanNilai(base64Data);

        // Assert
        assertEquals(1, result.get("jumlah"));
        assertEquals(100, result.get("total"));
        assertEquals("100.00", result.get("rataRata"));
        assertEquals(100, result.get("nilaiTertinggi"));
        assertEquals(100, result.get("nilaiTerendah"));
    }

    @Test
    @DisplayName("Mengembalikan error untuk Base64 tidak valid")
    void perolehanNilai_ShouldReturnErrorForInvalidBase64() {
        // Arrange
        HomeController controller = new HomeController();

        // Act
        Map<String, Object> result = controller.perolehanNilai("invalid-base64");

        // Assert
        assertEquals("Format Base64 tidak valid", result.get("error"));
    }

    @Test
    @DisplayName("Mengembalikan statistik nilai dengan data yang mengandung spasi")
    void perolehanNilai_ShouldHandleDataWithSpaces() {
        // Arrange
        HomeController controller = new HomeController();
        String base64Data = java.util.Base64.getEncoder().encodeToString("80, 85, 90, 75, 95".getBytes());

        // Act
        Map<String, Object> result = controller.perolehanNilai(base64Data);

        // Assert
        assertEquals(5, result.get("jumlah"));
        assertEquals(425, result.get("total"));
        assertNull(result.get("error"));
    }

    // Test untuk metode perbedaanL - data dengan L dan L terbalik
    @Test
    @DisplayName("Menghitung perbedaan L dan L terbalik dengan benar")
    void perbedaanL_ShouldReturnCorrectDifference() {
        // Arrange
        HomeController controller = new HomeController();
        String base64Data = java.util.Base64.getEncoder().encodeToString("L\nL\nɭ\nL".getBytes());

        // Act
        Map<String, Object> result = controller.perbedaanL(base64Data);

        // Assert
        assertEquals(3, result.get("jumlahL"));
        assertEquals(1, result.get("jumlahLTerbalik"));
        assertEquals(2, result.get("perbedaan"));
        assertEquals("L\nL\nɭ\nL", result.get("data"));
        assertNull(result.get("error"));
    }

    @Test
    @DisplayName("Menghitung hanya L biasa")
    void perbedaanL_ShouldHandleOnlyL() {
        // Arrange
        HomeController controller = new HomeController();
        String base64Data = java.util.Base64.getEncoder().encodeToString("L\nL\nL".getBytes());

        // Act
        Map<String, Object> result = controller.perbedaanL(base64Data);

        // Assert
        assertEquals(3, result.get("jumlahL"));
        assertEquals(0, result.get("jumlahLTerbalik"));
        assertEquals(3, result.get("perbedaan"));
    }

    @Test
    @DisplayName("Menghitung hanya L terbalik")
    void perbedaanL_ShouldHandleOnlyReverseL() {
        // Arrange
        HomeController controller = new HomeController();
        String base64Data = java.util.Base64.getEncoder().encodeToString("ɭ\nɭ".getBytes());

        // Act
        Map<String, Object> result = controller.perbedaanL(base64Data);

        // Assert
        assertEquals(0, result.get("jumlahL"));
        assertEquals(2, result.get("jumlahLTerbalik"));
        assertEquals(2, result.get("perbedaan"));
    }

    @Test
    @DisplayName("Mengembalikan perbedaan 0 untuk jumlah L dan L terbalik sama")
    void perbedaanL_ShouldReturnZeroForEqualCount() {
        // Arrange
        HomeController controller = new HomeController();
        String base64Data = java.util.Base64.getEncoder().encodeToString("L\nɭ\nL\nɭ".getBytes());

        // Act
        Map<String, Object> result = controller.perbedaanL(base64Data);

        // Assert
        assertEquals(2, result.get("jumlahL"));
        assertEquals(2, result.get("jumlahLTerbalik"));
        assertEquals(0, result.get("perbedaan"));
    }

    @Test
    @DisplayName("Mengembalikan error untuk Base64 tidak valid di perbedaanL")
    void perbedaanL_ShouldReturnErrorForInvalidBase64() {
        // Arrange
        HomeController controller = new HomeController();

        // Act
        Map<String, Object> result = controller.perbedaanL("invalid-base64");

        // Assert
        assertEquals("Format Base64 tidak valid", result.get("error"));
    }

    // Test untuk metode palingTer - kata terbanyak dan tersedikit
    @Test
    @DisplayName("Menemukan kata terbanyak dan tersedikit dengan benar")
    void palingTer_ShouldReturnMostAndLeastFrequentWords() {
        // Arrange
        HomeController controller = new HomeController();
        String text = "java spring java boot spring java";
        String base64Data = java.util.Base64.getEncoder().encodeToString(text.getBytes());

        // Act
        Map<String, Object> result = controller.palingTer(base64Data);

        // Assert
        assertEquals("java", result.get("kataTerbanyak"));
        assertEquals(3, result.get("jumlahTerbanyak"));
        assertEquals("boot", result.get("kataTersedikit"));
        assertEquals(1, result.get("jumlahTersedikit"));
        assertNull(result.get("error"));
    }

    @Test
    @DisplayName("Menangani kasus semua kata memiliki frekuensi sama")
    void palingTer_ShouldHandleAllWordsSameFrequency() {
        // Arrange
        HomeController controller = new HomeController();
        String text = "java spring boot";
        String base64Data = java.util.Base64.getEncoder().encodeToString(text.getBytes());

        // Act
        Map<String, Object> result = controller.palingTer(base64Data);

        // Assert
        // Harus mengembalikan salah satu kata dengan frekuensi 1
        assertEquals(1, result.get("jumlahTerbanyak"));
        assertEquals(1, result.get("jumlahTersedikit"));
    }

    @Test
    @DisplayName("Menangani teks dengan berbagai spasi")
    void palingTer_ShouldHandleVariousSpaces() {
        // Arrange
        HomeController controller = new HomeController();
        String text = "  java   spring  java  boot  ";
        String base64Data = java.util.Base64.getEncoder().encodeToString(text.getBytes());

        // Act
        Map<String, Object> result = controller.palingTer(base64Data);

        // Assert
        assertEquals("java", result.get("kataTerbanyak"));
        assertEquals(2, result.get("jumlahTerbanyak"));
    }

    @Test
    @DisplayName("Mengembalikan error untuk Base64 tidak valid di palingTer")
    void palingTer_ShouldReturnErrorForInvalidBase64() {
        // Arrange
        HomeController controller = new HomeController();

        // Act
        Map<String, Object> result = controller.palingTer("invalid-base64");

        // Assert
        assertEquals("Format Base64 tidak valid", result.get("error"));
    }

    // Test untuk helper method getProgramStudi menggunakan Reflection
    @Test
    @DisplayName("Mengembalikan nama program studi yang benar untuk semua prefix")
    void getProgramStudi_ShouldReturnCorrectStudyProgramForAllCases() throws Exception {
        // Arrange
        HomeController controller = new HomeController();
        Method method = HomeController.class.getDeclaredMethod("getProgramStudi", String.class);
        method.setAccessible(true);

        // Act & Assert untuk semua kasus
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
        assertEquals("Program Studi Tidak Dikenal", method.invoke(controller, "ABC"));
        assertEquals("Program Studi Tidak Dikenal", method.invoke(controller, ""));
      }

    // Test constructor
    @Test
    @DisplayName("Constructor harus berhasil membuat instance")
    void constructor_ShouldCreateInstance() {
        // Arrange & Act
        HomeController controller = new HomeController();

        // Assert
        assertNotNull(controller);
    }
}