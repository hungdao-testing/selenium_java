package com.aspire.loan.config;

import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


public class ProxyConfig {

    private BrowserMobProxyServer proxyServer;

    public BrowserMobProxyServer getProxyServer() {
        return proxyServer;
    }

    public ProxyConfig() {
        this.proxyServer = new BrowserMobProxyServer();
    }

    private static Function<Proxy, WebDriver> chromeProxy = (p) -> {
        ChromeOptions options = new ChromeOptions();
        options.setProxy(p);
        return new ChromeDriver(options);
    };

    private static Function<Proxy, WebDriver> firefoxProxy = (p) -> {
        FirefoxOptions options = new FirefoxOptions();
        options.setProxy(p);
        return new FirefoxDriver(options);
    };

    private static Map<String, Function<Proxy, WebDriver>> MAP = new HashMap<>();
    static {
        MAP.put("firefox", firefoxProxy);
        MAP.put("chrome", chromeProxy);
    }

    private Proxy setUpProxy(){
        this.proxyServer.start();
        this.proxyServer.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
        this.proxyServer.newHar();
        return ClientUtil.createSeleniumProxy(proxyServer);
    }

    protected void tearDownProxy(){
        proxyServer.abort();
    }

    public void attachProxyToBrowser(WebDriver driver){
        String browserName = ((RemoteWebDriver)driver).getCapabilities().getBrowserName();
        MAP.get(browserName).apply(this.setUpProxy());
    }


}
