package com.example.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import org.testng.annotations.AfterSuite;


public class SeleniumTests {
    private WebDriver driver;
    
    // Método para pausar el navegador
    private void pause(long milis){
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e){
        }
    }
    
    // Método para limpiar la calculadora
    private void clearCalculator(){
        driver.findElement(By.name("clearSimple")).click();
        driver.findElement(By.name("clearAdvanced")).click();
    }
    
    // Método para llenar los dos números
    // en la calculadora simple
    private void fillSimpleNums(String a, String b){
        driver.findElement(By.id("a")).clear();
        driver.findElement(By.id("b")).clear();

        driver.findElement(By.id("a")).sendKeys(a);
        driver.findElement(By.id("b")).sendKeys(b);
    }
    
    // Método para llenar el número
    // de la calculadora avanzada
    private void fillAdvancedNum(String c){
        driver.findElement(By.id("c")).clear();
        driver.findElement(By.id("c")).sendKeys(c);
    }
    
    // Antes de todos los test iniciamos el web driver
    // y nos dirigimos a la dirección del proyecto
    @BeforeSuite
    public void setUp(){
        DesiredCapabilities caps = new DesiredCapabilities();
        /*
            NOTA:
        
            Para resolver el ejercicio usé el Chrome webdriver de Linux
            la diferencia es que no tiene la extensión .exe
         */
        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver");
        driver = new ChromeDriver(caps);
        
        driver.manage().window();//.maximize();
        driver.navigate().to("http://localhost:8080/calculadora");
        pause(4000);
        // se me reinicia el servidor
        // por ello refresco la página
        driver.navigate().refresh();
        pause(1000);
    }
    
    // Después de todos los test cerramos el navegador
    @AfterSuite
    public void tearDown(){
        driver.close();
    }
    
    // Después de cada test pausamos el navegador 2 segundos
    // para ver el resultado
    // luego limpiamos la calculadora
    @AfterMethod
    public void tearDownTest() {
        pause(3000);
        clearCalculator();
    }

    /* Tests Calculadora Simple */
    
    // Test botón clear
    @Test
    public void testClearSimple(){
        driver.findElement(By.name("clearSimple")).click();
        String a = driver.findElement(By.id("a")).getAttribute("value");
        String b = driver.findElement(By.id("b")).getAttribute("value");
        
        assertEquals(a, b);
        assertEquals(a, "0");
    }
    
    // Test suma de dos números
    @Test
    public void testAdd() throws InterruptedException{
        fillSimpleNums("7", "8");
        driver.findElement(By.name("add")).click();
        
        String res = driver.findElement(By.xpath("/html/body/form/h1[2]/p")).getText();
        assertEquals("Resultado: 15", res);
        
        fillSimpleNums("0", "-1");
        driver.findElement(By.name("add")).click();
        
        res = driver.findElement(By.xpath("/html/body/form/h1[2]/p")).getText();
        assertEquals("Resultado: -1", res);
    }
    
    // Test resta de dos números
    @Test
    public void testSubstract() throws InterruptedException{
        fillSimpleNums("4", "-9");
        driver.findElement(By.name("subtract")).click();
        
        String res = driver.findElement(By.xpath("/html/body/form/h1[2]/p")).getText();
        assertEquals("Resultado: 13", res);

        fillSimpleNums("1", "8");
        driver.findElement(By.name("subtract")).click();
        
        res = driver.findElement(By.xpath("/html/body/form/h1[2]/p")).getText();
        assertEquals("Resultado: -7", res);
    }
    
    // Test multiplicación de dos números
    @Test
    public void testMultiply() throws InterruptedException{
        fillSimpleNums("-9", "20");
        driver.findElement(By.name("multiply")).click();
        
        String res = driver.findElement(By.xpath("/html/body/form/h1[2]/p")).getText();
        assertEquals("Resultado: -180", res);
        
        fillSimpleNums("111", "111");
        driver.findElement(By.name("multiply")).click();
        
        res = driver.findElement(By.xpath("/html/body/form/h1[2]/p")).getText();
        assertEquals("Resultado: 12321", res);
    }
    
    // Test división de dos números
    @Test
    public void testDivide() throws InterruptedException{
        fillSimpleNums("60", "5");
        driver.findElement(By.name("divide")).click();
        
        String res = driver.findElement(By.xpath("/html/body/form/h1[2]/p")).getText();
        assertEquals("Resultado: 12.0", res);

        fillSimpleNums("94", "5");
        driver.findElement(By.name("divide")).click();
        
        res = driver.findElement(By.xpath("/html/body/form/h1[2]/p")).getText();
        assertEquals("Resultado: 18.8", res);
    }
    
    
    /* Tests Calculadora Avanzada */
    
    // Test botón clear
    @Test
    public void testClearAdvanced(){
        driver.findElement(By.name("clearAdvanced")).click();
        String c = driver.findElement(By.id("c")).getAttribute("value");
        assertEquals(c, "0");
    }
    
    // Test enésimo Fibonacci
    @Test
    public void testFibonacci() throws InterruptedException{
        fillAdvancedNum("8");
        driver.findElement(By.name("fibonacci")).click();
        
        String res = driver.findElement(By.xpath("/html/body/form/h1[2]/p")).getText();
        assertEquals("Resultado: 21", res);
        
        fillAdvancedNum("10");
        driver.findElement(By.name("fibonacci")).click();
        
        res = driver.findElement(By.xpath("/html/body/form/h1[2]/p")).getText();
        assertEquals("Resultado: 55", res);
    }
    
    // Test factorial de un número
    @Test
    public void testFactorial() throws InterruptedException{
        fillAdvancedNum("0");
        driver.findElement(By.name("factorial")).click();
        
        String res = driver.findElement(By.xpath("/html/body/form/h1[2]/p")).getText();
        assertEquals("Resultado: 1", res);
        
        fillAdvancedNum("9");
        driver.findElement(By.name("factorial")).click();
        
        res = driver.findElement(By.xpath("/html/body/form/h1[2]/p")).getText();
        assertEquals("Resultado: 362880", res);
    }
    
    // Test raíz cuadrada de un número
    @Test
    public void testSqrt() throws InterruptedException{
        fillAdvancedNum("4");
        driver.findElement(By.name("sqrt")).click();
        
        String res = driver.findElement(By.xpath("/html/body/form/h1[2]/p")).getText();
        assertEquals("Resultado: 2.0", res);
        
        fillAdvancedNum("10000");
        driver.findElement(By.name("sqrt")).click();
        
        res = driver.findElement(By.xpath("/html/body/form/h1[2]/p")).getText();
        assertEquals("Resultado: 100.0", res);
    }
    
    // Test número elevado a sí mismo
    @Test
    public void testPower() throws InterruptedException{
        fillAdvancedNum("-5");
        driver.findElement(By.name("power")).click();
        
        String res = driver.findElement(By.xpath("/html/body/form/h1[2]/p")).getText();
        assertEquals("Resultado: 25", res);
        
        fillAdvancedNum("60");
        driver.findElement(By.name("power")).click();
        
        res = driver.findElement(By.xpath("/html/body/form/h1[2]/p")).getText();
        assertEquals("Resultado: 3600", res);
    }
}
