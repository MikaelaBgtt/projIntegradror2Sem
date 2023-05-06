/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.api.bd2;

import com.mycompany.api.bd2.daos.clienteDAO;
import com.mycompany.api.bd2.models.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author CodeCats
 */
public class CadastroClienteADMController {

    @FXML
    private Button fecharTela;
    @FXML
    private Button minimizarTela;
    @FXML
    private Label nometelaatual;
    @FXML
    private ImageView imagemUser;
    @FXML
    private Label nomeUsuario;
    @FXML
    private Button botaoSair;
    @FXML
    private Button menuUsuario;
    @FXML
    private Button menuCR;
    @FXML
    private Button menuCliente;
    @FXML
    private Button menuAprovar;
    @FXML
    private Button menuRelatorio;
    @FXML
    private TextField entradaCNPJ;
    @FXML
    private TextField entradaRS;
    @FXML
    private Button botaoAdicionar;
    @FXML
    private Button botaoLimpar;
    @FXML
    private Button botaoEditar;
    @FXML
    private Button botaoInativar;
    @FXML
    private Button botaoAtivar;
    @FXML
    private TableView<Cliente> tabelaCadastroCliente;
    @FXML
    private TableColumn<?, ?> colunaCNPJ;
    @FXML
    private TableColumn<?, ?> colunaRS;

    private List<String> obs = new ArrayList<>();
    private ObservableList<String> opcoes = FXCollections.observableArrayList();
    private List<Cliente> lisClientes = new ArrayList<>();
    private ObservableList<Cliente> observableListCliente = FXCollections.observableArrayList();

    long valorDoItemSelecionado;

    public void initialize() {
        nomeUsuario.setText("*nome do usuário*");

        botaoLimpar.setOnAction(event -> limparCampos());
        carregarTabelaCliente();
        tabelaCadastroCliente.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) { // Verifica se é um único clique
                    Cliente item = (Cliente) tabelaCadastroCliente.getSelectionModel().getSelectedItem(); // Obtém o item selecionado
                    valorDoItemSelecionado = item.getCnpj();
                    entradaCNPJ.setText(String.valueOf(item.getCnpj()));
                    entradaRS.setText(item.getRazao_social());
                    System.out.println("Item selecionado: " + valorDoItemSelecionado); // Imprime no console
                }
            }
        });

    }

    @FXML
    public void carregarTabelaCliente() {
        lisClientes.clear();

        clienteDAO clientedao = new clienteDAO();

        lisClientes.addAll(clientedao.getClientes());
        observableListCliente.setAll(lisClientes);
        tabelaCadastroCliente.setItems(observableListCliente);

        colunaCNPJ.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        colunaRS.setCellValueFactory(new PropertyValueFactory<>("razao_social"));
    }

    @FXML
    private void BotaoAdicionar(ActionEvent event) {
        System.out.println("botão adicionar");
        if (entradaCNPJ.getText().isEmpty() || entradaRS.getText().isEmpty()) {
            System.out.println("Preencha todos os campos - cadastro de cliente");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Preencha todos os campos");
            alert.setHeaderText(null);
            alert.setContentText("Alguns dos campos não foi preenchido");
            alert.showAndWait();
        } else {
            Cliente cliente = new Cliente();
            cliente.setCnpj(Long.parseLong(entradaCNPJ.getText()));
            System.out.println("set cnpj ok");
            cliente.setRazao_social(entradaRS.getText());
            System.out.println("set rs ok");
            cliente.setStatus_cliente("ativo");
            System.out.println("set status ok");

            clienteDAO clienteDao = new clienteDAO();
            clienteDao.save(cliente);
            System.out.println("save ok");
            carregarTabelaCliente();
        }
    }

    @FXML
    public void limparCampos() {
        System.out.println("botão limpar");
        entradaCNPJ.clear();
        entradaRS.clear();
    }

    @FXML
    private void BotaoEditar(ActionEvent event) {
        System.out.println("botão editar");

        // verifica se alguma linha foi selecionada
        if (tabelaCadastroCliente.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText(null);
            alert.setContentText("Tem certeza que deseja atualizar os dados do cliente?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // o usuário clicou em "OK", continue com a ação
                Cliente clienteSelecionado = tabelaCadastroCliente.getSelectionModel().getSelectedItem();

                String novoNome = entradaRS.getText();
                if (!novoNome.isEmpty()) {
                    // atualiza o objeto Cliente com o novo nome
                    clienteSelecionado.setRazao_social(novoNome);

                    // salva o objeto atualizado no banco de dados
                    clienteDAO clienteDao = new clienteDAO();
                    clienteDao.update(clienteSelecionado);

                    // atualiza a tabela com as novas informações
                    carregarTabelaCliente();
                    limparCampos();
                }
            } else {
                limparCampos();
                System.out.println("Cancelado");
            }

        } else {
            System.out.println("Nenhuma linha selecionada");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nenhuma linha selecionada");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione uma linha da tabela para editar");
            alert.showAndWait();
        }
    }

    @FXML
    private void BotaoInativar(ActionEvent event) {
        System.out.println("botão inativar");
        clienteDAO clientedao = new clienteDAO();
        Cliente cliente = new Cliente();

        String razao_social = clientedao.getClientebyCNPJ(valorDoItemSelecionado).getRazao_social();
        cliente.setCnpj(valorDoItemSelecionado);
        cliente.setRazao_social(razao_social);
        cliente.setStatus_cliente("inativo");
        clientedao.update(cliente);
        carregarTabelaCliente();
    }

    @FXML
    private void BotaoAtivar(ActionEvent event) {
        System.out.println("botão ativar");
        clienteDAO clientedao = new clienteDAO();
        Cliente cliente = new Cliente();

        String razao_social = clientedao.getClientebyCNPJ(valorDoItemSelecionado).getRazao_social();
        cliente.setCnpj(valorDoItemSelecionado);
        cliente.setRazao_social(razao_social);
        cliente.setStatus_cliente("ativo");
        clientedao.update(cliente);
        carregarTabelaCliente();
    }

    @FXML
    private void BotaoFechar(ActionEvent event) {
        System.out.println("botão fechar");
    }
}
