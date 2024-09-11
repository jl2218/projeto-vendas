# Sistema de Ponto de Venda (PDV) Desktop

Este projeto foi desenvolvido como parte de um trabalho acadêmico e consiste em um sistema de ponto de venda (PDV) desktop. O sistema foi criado em Java com uma interface gráfica e utiliza MySQL como banco de dados para gerenciar produtos, vendas e usuários. O projeto segue o padrão **Model-View-Controller (MVC)** para a organização de pacotes e separar as responsabilidades da aplicação.

## Funcionalidades

- **Gestão de Produtos:** Cadastro, edição e exclusão de produtos.
- **Realização de Vendas:** Registro de vendas com cálculo automático do total.
- **Gerenciamento de Usuários:** Cadastro e gerenciamento de usuários que podem acessar o sistema.
- **Interface Gráfica:** Interface de usuário intuitiva para facilitar o uso do sistema.

## Tecnologias Utilizadas

- **Java:** Linguagem de programação para desenvolvimento do aplicativo.
- **MySQL:** Banco de dados relacional para armazenamento de dados.
- **JDBC:** Conector Java para comunicação com o banco de dados MySQL.

## Estrutura do Projeto

O projeto segue o padrão **Model-View-Controller (MVC)** para uma melhor organização e separação das responsabilidades:

- **Model:** Contém as classes que representam os dados e a lógica de negócios do sistema. Inclui as classes de entidades, operações de banco de dados e lógica de processamento.
- **View:** Contém as classes responsáveis pela interface gráfica do usuário. Utiliza Swing para construir as janelas e os formulários com os quais o usuário interage.
- **Controller:** Contém as classes que atuam como intermediários entre o Model e a View. Gerencia as interações do usuário, atualiza o Model e a View conforme necessário.
