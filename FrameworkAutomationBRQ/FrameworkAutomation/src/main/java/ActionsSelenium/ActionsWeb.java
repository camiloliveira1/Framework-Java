package ActionsSelenium;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import TestBaseSelenium.TestBase;


	public class ActionsWeb extends TestBase {
		
	/////FUNCIONALIDADES DE A��ES (STEPS)/////
		//ABRE O BROWSER COM A URL
		public void openBrowser (String browser, String url) throws IOException
		{
			getStepName = getNameMethodElement ();
			try
			{
				switch (browser){
				case "Chrome":
					System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
					//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "\\src\\test\\resources\\chromedriver.exe");
					driver = new ChromeDriver();
					break;
				case "Firefox":
					System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
					driver = new FirefoxDriver();				
					break;			
				case "IE":
					System.setProperty("webdriver.ie.driver", "src/main/resources/drivers/IEDriverServer.exe");
					driver = new InternetExplorerDriver();				
				case "AllBrowsers":
				}
				
				driver.get(url);			
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);			
				AssertStep(true,"Url: '"+ url +"' digitada");
			}
			catch (Exception e)
			{
				AssertStep(false, "Erro ao digitar Url: '"+url+"' '"+e+"'");
			}			
		}
		
		//DIGITA O VALOR NO CAMPO INPUT
		public void setInputBox(String byelement, String element, String value) throws IOException
		{
			getStepName = getNameMethodElement ();
			try
			{
				indentifyElemnt(byelement, element);
				focusElement();
				this.element.sendKeys(value);
				AssertStep(true,"Valor '"+ value +"' foi digitado no inputBox: '"+element+"'");						
			}
			catch (Exception e)
			{
				AssertStep(false,"Erro ao digitar o valor: '"+ value +"' no inputBox: '"+element+"'");
			}
		}
		
		//SELECT LISTBOX
		public void selectListBox (String byelement, String element, String valueSelect) throws InterruptedException, IOException{
			
			getStepName = getNameMethodElement ();			
			try
			{
				indentifyElemnt(byelement, element);
				focusElement();
				Select select = new Select (this.element);
				Thread.sleep(2000);
				select.selectByVisibleText(valueSelect);
				AssertStep(true,"Valor '"+ valueSelect +"' foi selecionado: '"+element+"'");						
			}
			catch (Exception e)
			{
				try{
					
					driver.findElement(By.xpath("//ul/li[contains(text(), "+valueSelect+")]")).click();
					
				}catch (Exception ee){
					AssertStep(false,"Erro ao selecionar o valor: '"+ valueSelect +"' no listbox: '"+element+"'");
				}				
				
			}		

	}
			
		//CLICK Radio
		public void clickRadio (String byelement, String element) throws IOException{
			getStepName = getNameMethodElement ();
			try
			{
				indentifyElemnt(byelement, element);
				focusElement();
				AssertStep(true,"Selecionado Radio button: '"+element+"'");
				this.element.click();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			}
			catch (Exception e)
			{
				AssertStep(false,"Erro ao selecionar o Radio: '"+element+"'");
			}
			
		}
		
		//CLICK NO ELEMENTO
		public void clickElement (String byelement, String element) throws IOException{
			getStepName = getNameMethodElement ();
			try
			{
				indentifyElemnt(byelement, element);
				focusElement();
				AssertStep(true,"Realizado click no elemento '"+element+"'");
				this.element.click();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			}
			catch (Exception e)
			{
				AssertStep(false,"Erro ao clicar no elemento '"+element+"'");
			}
			
		}
		
		//Verificar se a grid cont�m e apenas se cont�m um valor determinado.
		public void searchValueGrid(String byelement, String element, String searchValue) throws IOException{
			getStepName = getNameMethodElement ();
			boolean verf = false;

			try
			{				
				indentifyElemnt(byelement, element);
				focusElement();
				List <WebElement> trs = (ArrayList <WebElement>) this.element.findElements(By.tagName("tr"));
				for (WebElement tr : trs) {
					List<WebElement> cells = tr.findElements(By.tagName("td"));

					for (WebElement cell:cells){
						if (searchValue.equalsIgnoreCase(cell.getText())){
							AssertStep(true,"Valor "+searchValue +" encntrado na Grid :"+ "'"+element+"'");
							verf = true;
						}
					}
				}
				if (verf != true){
					AssertStep(false,"Valor "+searchValue +" N�O encntrado na Grid :"+ "'"+element+"'");
				}
			}catch (Exception e){
				AssertStep(false,"Erro ao localizar a grid '"+element+"'");
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//Clicar no valor solicitado da Grid.
		public void clickValueGrid(String byelement, String element, String searchValue) throws IOException{
			getStepName = getNameMethodElement ();
			boolean verf = false;

			try
			{				
				indentifyElemnt(byelement, element);
				focusElement();
				List <WebElement> trs = (ArrayList <WebElement>) this.element.findElements(By.tagName("tr"));
				for (WebElement tr : trs) {
					List<WebElement> cells = tr.findElements(By.tagName("td"));

					for (WebElement cell:cells){
						if (searchValue.equalsIgnoreCase(cell.getText())){
							cell.click();
							driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							AssertStep(true,"Click no valor "+searchValue +" encntrado na Grid :"+ "'"+element+"'");
							verf = true;
						}
					}
				}
				if (verf != true){
					AssertStep(false,"Valor "+searchValue +" N�O encntrado na Grid :"+ "'"+element+"'");
				}
			}catch (Exception e){
				AssertStep(false,"Erro ao localizar a grid '"+element+"'");
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
			
		
	/////ASSERTS PARA RESULTADOS FINAIS DO CT/////
		//ASSERT - VERIFICA SE O ELEMENTO INFORMADO EST� VISIVEL - ASSERT PARA RESULTADO DE TESTE
		public void assertElementDisplayed(String byelement, String element) throws IOException{
			getStepName = getNameMethodElement ();			
			try{
				indentifyElemnt(byelement, element);
				Assert.assertEquals(true, this.element.isDisplayed());
				focusElement();
				AssertStep(true,"<html>Teste '"+nomeTest+"' concluido com �xito.<br/>Elemento: "+this.element+" carregado.</html>");
			}catch (Exception e)
			{
				AssertStep(false,"<html>Teste '"+nomeTest+"' n�o obteve o resultado esperado.<br/>Elemento: " +this.element+" N�O foi carregado.</html>");
			}
				
		}
		
		public void assertValues(String byelement, String element, String expected) throws IOException{
			getStepName = getNameMethodElement ();
			try{
				
				indentifyElemnt(byelement, element);
				focusElement();
					if (this.element.getText().equals(expected))
					{
						AssertStep(true,"<html>Teste '"+nomeTest+"' executado com �xito.<br/> VALOR ESPERADO: "+ expected +"<br/> VALOR DE RETORNO: "+this.element.getText()+"</html>");
					}else
					{
						AssertStep(false,"<html>Teste '"+nomeTest+"' n�o obteve �xito.<br/> VALOR ESPERADO: "+ expected +"<br/> VALOR DE RETORNO: "+this.element.getText()+"</html>");
					}
				}
			catch (Exception e)
					{
						AssertStep(false,"<html>Elemento n�o localizado: <br/>"+e +"</html>");
					}
				
		}	
		
		
		
		
		
		
		
		
		
		
		
	//////FUNCIONALIDADES DE APOIO � EXECU��O/////
		//CRIA ASSERTS PARA CADA STEP (RELAT�RIO)
		public void AssertStep (Boolean step, String msg) throws IOException
		{
			if (step == false)
			{		
				childTest = test.createNode(getStepName).fail(msg).addScreenCaptureFromPath(captureScreen(driver,getStepName));
			}else if (step == true){
			
				childTest = test.createNode(getStepName).pass(msg).addScreenCaptureFromPath(captureScreen(driver,getStepName));
			}
		}
		
		//CAPTURA EVIDENCIA DO SISTEMA
		public String captureScreen(WebDriver driver, String screenName) throws IOException
		{
			String pathCaptureimg = pathProject + "\\Repositorios\\imgs\\"+ nomeSuiteTest +"\\" +nomeTest+"\\";
			File file = new File(pathCaptureimg);
	        file.mkdir();
			TakesScreenshot screen = (TakesScreenshot) driver;
			File src = screen.getScreenshotAs(OutputType.FILE);
			
			String dest = pathCaptureimg + screenName + "_"+ data +".png";
			
			File target = new File(dest);
			FileUtils.copyFile(src, target);
			
			return dest;
		}
		
		//IDENTIFICA QUAL O TIPO DO ELEMENTO INFORMADO (NAME, ID, CSS, XPATH)
		public void indentifyElemnt(String byelement, String element) throws IOException{
			try{
				switch (byelement) 
				{			
				case "name":
					this.element = driver.findElement(By.name(element));
					break;
				case "id":
					this.element = driver.findElement(By.id(element));
					break;
				case "class":				
					this.element = driver.findElement(By.className(element));				
					break;
				case "xpath":
					this.element = driver.findElement(By.xpath(element));
				}	
			}catch (Exception e){
				
			}
		}
		
		//DA FOCUS NO ELMENTO ANTES DE TIRAR EVIDENCIA.
		public void focusElement(){
			new Actions(driver).moveToElement(this.element).perform();
		}
		
		public String getNameMethodElement (){
			String nameMethod = Thread.currentThread().getStackTrace()[3].getMethodName();
			return nameMethod;
		}
		
		
		
		
		
		
		
		
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

