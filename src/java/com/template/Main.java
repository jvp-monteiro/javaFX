package com.template;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;

/*
 * Classe principal da aplicação.
 * Responsável por iniciar o JavaFX, carregar a interface gráfica
 * definida no arquivo main.fxml e exibir a janela do sistema.
 */
public class Main extends Application {

    /*
     * Método executado na inicialização do sistema.
     * Carrega a tela principal e configura a janela da aplicação.
     */
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("main.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);

        scene.getStylesheets().add(
                getClass().getResource("style.css").toExternalForm()
        );

        stage.setTitle("Cadastro de Bikes");
        stage.setScene(scene);
        stage.show();
    }

    /*
     * Ponto de entrada da aplicação.
     * Inicia o ambiente JavaFX.
     */
    public static void main(String[] args) {
        launch();
    }
}