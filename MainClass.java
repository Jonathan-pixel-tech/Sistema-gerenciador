
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainClass {

    final static Scanner INPUT = new Scanner(System.in);

    public static void main(String[] args) {
        // INPUT.nextLine();
        System.out.println("REDE DE SUPERMERCADOS DEVmais");
        System.out.println("******************************************************");
        System.out.println("Bem vindo ao sistema de gerenciamento de mercados e produtos da rede");
        System.out.println("******************************************************");

        while (true) {
            System.out.printf("MENU INICIAL \n"
                    + " Digite '1' para Acessar as opções de Mercados da rede. \n"
                    + " Digite '2' para acessar as opções de Produtos na distribuidora da rede. \n"
                    + " Digite '3' para finalizar o programa \n");
            int resposta = INPUT.nextInt();
            if (resposta == 3) {
                System.out.println("Ok! Programa finalizado! Espero te ver em breve");
                break;
            }

            if (resposta == 1) {
                while (true) {
                    System.out.printf("VOCÊ ACESSOU AS OPÇÕES DE MERCADO. \n"
                            + " Digite '1' Para cadastrar um novo mercado na rede. \n"
                            + " Digite '2' Para buscar mercados já existentes. \n"
                            + " Digite '3'Para editar os dados de um mercado da rede. \n"
                            + " Digite '4' para remover um mercado em caso de fechamento \n"
                            + " Digite '5' para finalizar as opções de mercado. \n");
                    resposta = INPUT.nextInt();

                    if (resposta == 5) {
                        break;
                    }
                    switch (resposta) {
                        case 1:
                            cadastrarMercado();
                            break;
                        case 2:
                            buscarMercado();
                            break;
                        case 3:
                            editarMercado();
                            break;
                        case 4:
                            removerMercado();
                            break;

                    }
                }
            } else if (resposta == 2) {
                while (true) {
                    System.out.printf("VOCÊ ACESSOU AS OPÇÕES DE PRODUTOS. \n"
                            + " Digite '1' para cadastrar novos produtos. \n"
                            + " Digite '2' para fazer buscas por produtos. \n"
                            + " Digite '3' para editar produtos. \n"
                            + " Digite '4' para remover produtos \n"
                            + " Digite '5' para finalizar as opções de produtos \n");
                    resposta = INPUT.nextInt();

                    if (resposta == 5) {
                        break;
                    }

                    switch (resposta) {
                        case 1:
                            cadastrarProduto();
                            break;
                        case 2:
                            buscarProduto();
                            break;
                        case 3:
                            editarProduto();
                            break;
                        case 4:
                            removerProduto();
                            break;
                    }
                }
            }

        }

    }

    public static void cadastrarProduto() {
        // input usado para corrigir bug de leitura
        INPUT.nextLine();

        double preco = 0.0;

        System.out.println("Bem vindo ao sistema de cadastro de produtos");
        System.out.println();

        System.out.println("Por favor, digite o nome do produto a ser cadastrado ");
        String nome = INPUT.nextLine().toUpperCase();

        System.out.println("Por favor, digite o código do produto a ser cadastrado");
        String codigo = INPUT.nextLine().toUpperCase();

        System.out.println("Por favor, digite o preço do produto a ser cadastrado");
        preco = Double.parseDouble(INPUT.next().replace(",", "."));

        if (nome.isEmpty() || codigo.isEmpty() || preco == 0.0) {
            System.out.println("Atenção! Para realizar o cadastro é necessário que todos os campos estejam preenchidos! ");
            return;
        }
        if (Dados.produtos.isEmpty()) {
            Produto produto = new Produto(nome, codigo, preco);
            Dados.produtos.add(produto);
            System.out.println("Produto cadastrado com sucesso!");
            return;
        }

        for (Produto p : Dados.produtos) {
            if (p.getCodigo().equals(codigo)) {
                System.out.println("Não foi possível cadastrar o produto pois o código informado já foi cadastrado! ");
                return;
            }
        }
        Produto produto = new Produto(nome, codigo, preco);
        Dados.produtos.add(produto);
        System.out.println("Produto cadastrado com sucesso!");

    }

    public static void buscarProduto() {

        while (true) {
            System.out.printf(" Para buscar o produto por código digite '1'. \n"
                    + " Para buscar o produto por nome digite '2'. \n"
                    + " para sair da busca digite '3' \n");
            int resposta = INPUT.nextInt();
            if (resposta == 3) {
                break;
            }
            switch (resposta) {
                case 1:
                    buscaPorCodigo();
                    break;
                case 2:
                    buscaPorNome();
                    break;
            }
        }

    }

    public static Produto buscaPorNome() {
        INPUT.nextLine();
        System.out.println("Digite o nome do produto ");
        String nomeProduto = INPUT.nextLine().toUpperCase();
        for (Produto p : Dados.produtos) {
            if (p.getNome().equals(nomeProduto)) {
                System.out.println(p.getNome() + ", " + p.getCodigo() + ", " + "R$" + p.getPreco());
                return p;
            }
        }
        System.out.println("PRODUTO NÃO CADASTRADO ");
        Produto retornoVazio = new Produto("vazio", "xxx", 00);
        return retornoVazio;
    }

    public static Produto buscaPorCodigo() {
        System.out.println("Digite o código do produto ");
        String codigoProduto = INPUT.next().toUpperCase();
        for (Produto p : Dados.produtos) {
            if (p.getCodigo().equals(codigoProduto)) {
                System.out.println(p.getNome() + ", " + p.getCodigo() + ", " + "R$" + p.getPreco());
                return p;
            }
        }
        System.out.println("PRODUTO NÃO CADASTRADO ");
        Produto retornoVazio = new Produto("vazio", "xxx", 00);
        return retornoVazio;
    }

    public static void removerProduto() {
        Produto produto = new Produto();

        System.out.println("Para remover um produto antes faça a busca por ele. "
                + "Digite '1' para procurar por código e '2' para procurar por nome ou digite '3'"
                + " para parar as remoções ");
        int resposta = INPUT.nextInt();

        if (resposta == 3) {
            return;
        }
        String verificaExclusao;
        switch (resposta) {

            case 1:
                produto = buscaPorCodigo();
                // Verifica se existe o produto, pois, se não existir é retornado um objeto produto vazio
                if (produto.getNome().equals("vazio")) {
                    System.out.println("Não é possível remover um produto que não existe");
                    return;
                }
                System.out.println("Tem certeza que deseja excluir o produto " + produto.getNome() + "? 'S' para sim ou 'N' para não ");
                verificaExclusao = INPUT.next().toUpperCase();

                if (verificaExclusao.equals("N")) {
                    System.out.println("Ok! Nada foi excluido");
                    return;
                }
                Dados.produtos.remove(produto);
                System.out.println("Produto removido com sucesso!");

                break;
            case 2:
                produto = buscaPorNome();
                if (produto.getNome().equals("vazio")) {
                    System.out.println("Não é possível remover um produto que não existe");
                    return;
                }
                System.out.println("Tem certeza que deseja excluir o produto " + produto.getNome() + "? 'S' para sim ou 'N' para não ");
                verificaExclusao = INPUT.next().toUpperCase();

                if (verificaExclusao.equals("N")) {
                    System.out.println("Ok! Nada foi excluido");
                    return;
                }
                Dados.produtos.remove(produto);
                System.out.println("Produto removido com sucesso!");

                break;

        }

    }

    public static void editarProduto() {
        int referencia = 1;
        System.out.println("***************************************************");
        System.out.println("Escolha o número referente ao produto para editar ");

        for (Produto p : Dados.produtos) {
            System.out.println(referencia + " " + p.getNome() + ", " + p.getCodigo() + ", " + "R$" + p.getPreco());
            referencia++;
        }
        int resposta = INPUT.nextInt();
        Produto produtoEscolhido = new Produto();
        produtoEscolhido = Dados.produtos.get(resposta - 1);
        System.out.println("Os campos do produto a ser editado são os seguintes " + produtoEscolhido.getNome() + ", " + produtoEscolhido.getCodigo() + ", " + "R$" + produtoEscolhido.getPreco());
        System.out.println("Digite '1' para editar o nome do produto, digite '2' para editar o código, digite '3' para editar o preço ou digite '4' para finalizar as opções");

        int respostaAleteracao = INPUT.nextInt();
        if (respostaAleteracao == 4) {
            return;
        }

        switch (respostaAleteracao) {
            case 1:
                editarNomeProduto(resposta - 1);
                break;
            case 2:
                editarCodigoProduto(resposta - 1);
                break;
            case 3:
                editarPrecoProduto(resposta - 1);
                break;
        }

        referencia = 1;
    }

    public static void editarNomeProduto(int indice) {
        System.out.println("Digite o novo nome do produto ");
        String resposta = INPUT.next().toUpperCase();

        if (resposta.isEmpty()) {
            System.out.println("Não é possível aleterar o campo nome para vazio ");
            return;
        }
        Dados.produtos.get(indice).setNome(resposta);
        System.out.println("Produto alterado com sucesso! O produto  com a edição agora está assim: " + Dados.produtos.get(indice).getNome() + "," + Dados.produtos.get(indice).getCodigo() + " R$" + Dados.produtos.get(indice).getPreco());

    }

    public static void editarCodigoProduto(int indice) {
        System.out.println("Digite o novo código do produto");
        String resposta = INPUT.next().toUpperCase();
        if (resposta.isEmpty()) {
            System.out.println("Não é possível aleterar o campo código para vazio ");
            return;
        }
        for (Produto p : Dados.produtos) {
            if (p.getCodigo().equals(resposta)) {
                System.out.println("Não é possível alterar o código pois o código escolhido já existe ");
                return;
            }
        }

        Dados.produtos.get(indice).setCodigo(resposta);
        System.out.println("Produto alterado com sucesso! O produto  com a edição agora está assim: " + Dados.produtos.get(indice).getNome() + "," + Dados.produtos.get(indice).getCodigo() + " R$" + Dados.produtos.get(indice).getPreco());
    }

    public static void editarPrecoProduto(int indice) {
        System.out.println("Digite o novo preço do produto ");
        double resposta = INPUT.nextDouble();

        Dados.produtos.get(indice).setPreco(resposta);
        System.out.println("Produto alterado com sucesso! O produto  com a edição agora está assim: " + Dados.produtos.get(indice).getNome() + "," + Dados.produtos.get(indice).getCodigo() + " R$" + Dados.produtos.get(indice).getPreco());

    }

    public static void cadastrarMercado() {
        // input usado para corrigir bug de leitura
        INPUT.nextLine();

        if (Dados.produtos.isEmpty()) {
            System.out.println("###################################################################################################################################################");
            System.out.println("Atenção! Não foi possível cadastrar o novo mercado, pois não há produtos para enviar. Precisamos de produtos para enviar à nova filial. ");
            System.out.println("###################################################################################################################################################");
            return;
        }
        System.out.println("Quase lá! Para adicionar mais um mercado nessa rede campeã preencha os dados a seguir ");
        System.out.println("Por favor, digite o nome do novo supermercado da rede");
        String nome = INPUT.nextLine().toUpperCase();

        System.out.println("Por favor, digite o CNPJ do novo supermercado");
        String CNPJ = INPUT.nextLine().toUpperCase();

        System.out.println("Por favor, Digite a cidade em que se localiza o novo supermercado");
        String cidade = INPUT.nextLine();

        System.out.println("Por favor, Digite o bairro onde se localiza o novo supermercado");
        String bairro = INPUT.nextLine();

        Map<Produto, Integer> estoqueProdutos = new HashMap<>();

        System.out.println("Seção de Cadastro de produtos");

        listaProdutosExistente(Dados.produtos);

        while (true) {
            System.out.println("Escolha o número referente ao produto para cadastro-lo. Digite '0' para finalizar a escolha dos produtos");
            int resposta = INPUT.nextInt();
            if (resposta == 0) {
                break;
            }
            System.out.println("Você escolheu o produto " + Dados.produtos.get(resposta - 1).getNome() + ". Digite a quantidade do produto que deseja cadastrar");
            int quantidade = INPUT.nextInt();
            estoqueProdutos.put(Dados.produtos.get(resposta - 1), quantidade);
            System.out.println("Produto adicionado");
        }

        if (nome.isEmpty() || CNPJ.isEmpty() || cidade.isEmpty() || bairro.isEmpty() || estoqueProdutos.isEmpty()) {
            System.out.println("Atenção, todos os campos devem ser preenchidos");
            return;
        }

        if (Dados.mercados.isEmpty()) {
            Mercado mercado = new Mercado(nome, CNPJ, cidade, bairro, estoqueProdutos);
            Dados.mercados.add(mercado);
            System.out.println("Mercado adicionado a rede com sucesso!!");
            return;
        }

        for (Mercado m : Dados.mercados) {
            if (m.getNome().equals(nome)) {
                System.out.println("Não foi possível cadastrar, pois esse nome já está cadastrado");
                return;
            }
            if (m.getCNPJ().equals(CNPJ)) {
                System.out.println("Não foi possível cadastrar, pois esse CNPJ já está cadastrado");
                return;
            }
        }

        Mercado mercado = new Mercado(nome, CNPJ, cidade, bairro, estoqueProdutos);
        Dados.mercados.add(mercado);
        System.out.println("Mercado adicionado a rede com sucesso!!");
    }

    public static void buscarMercado() {
        System.out.printf("Digite 1 para listar todos os mercados existentes \n"
                + "Digite 2 para buscar mercados por Cidade \n"
                + "Digite 3 para listar mercados com base nos produtos cadastrados \n"
                + "Digite 4 para finalizar as opções de busca \n ");
        int resposta = INPUT.nextInt();

        if (resposta == 4) {
            return;
        }

        switch (resposta) {
            case 1:
                listaMercados();
                break;
            case 2:
                buscaMercadoPorCidade();
                break;
            case 3:
                buscaMercadoPorProduto();
                break;
        }
    }

    public static void listaMercados() {
        int indice = 1;
        System.out.println("***************************************************************************************************************************");
        for (Mercado m : Dados.mercados) {
            System.out.println(indice + " - " + "Nome: " + m.getNome() + "; CNPJ: " + m.getCNPJ() + ", Cidade: " + m.getCidade() + ", Bairro: " + m.getBairro());
            indice++;

            System.out.printf(" Produtos em estoque: ");
            for (Produto p : m.getEstoqueProdutos().keySet()) {
                System.out.println(" Produto: " + p.getNome() + " Quantidade do produto em estoque: " + m.getEstoqueProdutos().get(p));
            }
            System.out.println();
        }
        System.out.println("***************************************************************************************************************************");
    }

    public static void removerMercado() {
        INPUT.nextLine();

        System.out.println("Digite o nome ou o CNPJ para remover o mercado que deseja");
        String nomeOuCNPJ = INPUT.nextLine().toUpperCase();

        for (Mercado m : Dados.mercados) {
            if (m.getNome().equals(nomeOuCNPJ) || m.getCNPJ().equals(nomeOuCNPJ)) {
                System.out.println("Nome: " + m.getNome() + "; CNPJ: " + m.getCNPJ() + ", Cidade: " + m.getCidade() + ", Bairro: " + m.getBairro());
                System.out.println("Tem certeza que deseja excluir o mercado? Digite 'S' para sim ou 'N' para cancelar");
                String confirmacao = INPUT.nextLine().toUpperCase();
                if (confirmacao.equals("S")) {
                    Dados.mercados.remove(m);
                    System.out.println("Mercado removido da rede com sucesso!!");
                    return;
                } else {
                    System.out.println("Ok! Nada foi apagado!");
                    return;
                }

            }
        }
        System.out.println("O nome ou CNPJ pelo qual você procurou não existe");
    }

    public static void buscaMercadoPorCidade() {
        INPUT.nextLine();
        boolean existe = false;
        System.out.println("Digite o nome da cidade");
        String cidade = INPUT.nextLine().toUpperCase();

        for (Mercado m : Dados.mercados) {
            if (m.getCidade().equals(cidade)) {
                System.out.println("Nome: " + m.getNome() + "; CNPJ: " + m.getCNPJ() + ", Cidade: " + m.getCidade() + ", Bairro: " + m.getBairro());
                System.out.printf(" Produtos em estoque: ");
                for (Produto p : m.getEstoqueProdutos().keySet()) {
                    System.out.println(" Produto: " + p.getNome() + " Quantidade do produto em estoque: " + m.getEstoqueProdutos().get(p));
                }
                existe = true;
            }
        }
        if (existe == false) {
            System.out.println("Ainda não existem supermercados nessa cidade");
        }

    }

    public static void buscaMercadoPorProduto() {
        boolean existe = false;
        System.out.println("Digite o número relacionado ao produto que deseja buscar os mercados que os tenham");
        listaProdutosExistente(Dados.produtos);
        int escolha = INPUT.nextInt();
        String produtoBuscado = Dados.produtos.get(escolha - 1).getNome();

        System.out.println("Aqui estão os mercados que tem esse produto em seus estoques: ");
        for (Mercado m : Dados.mercados) {
            for (Produto p : m.getEstoqueProdutos().keySet()) {
                if (p.getNome().equals(produtoBuscado)) {
                    System.out.println("Nome: " + m.getNome() + "; CNPJ: " + m.getCNPJ() + ", Cidade: " + m.getCidade() + ", Bairro: " + m.getBairro());
                    existe = true;
                }
            }
        }
        if (existe == false) {
            System.out.println("Não há mercados na rede cadastrados com tais produtos2");
        }
    }

    public static void listaProdutosExistente(ArrayList<Produto> arrayProdutos) {
        int indice = 1;
        for (Produto produto : arrayProdutos) {
            System.out.println(indice + " - " + produto.getNome());
            indice++;
        }
    }

    public static void editarMercado() {
        INPUT.nextLine();
        listaMercados();
        System.out.println("Escolha o numero equivalente ao mercado que deseja editar ou digite '0' para fechar opções de edição");
        int indice = INPUT.nextInt();
        if (indice == 0) {
            return;
        }
        Mercado mercadoEscolhido = Dados.mercados.get(indice - 1);
        System.out.println("Os campos para edição são os seguintes: " + mercadoEscolhido.getNome() + ", " + mercadoEscolhido.getCNPJ() + ", " + mercadoEscolhido.getBairro() + ", " + mercadoEscolhido.getCidade());
        System.out.printf("Digite '1' para editar o nome do Mercado \n"
                + "Digite '2' para editar o CNPJ do mercado \n"
                + "Digite '3' para editar o Bairro do mercado \n"
                + "Digite '4' para editar a Cidade do mercado \n"
                + "Digite '5' para finalizar as opçoes de edição \n");

        int escolha = INPUT.nextInt();

        if (escolha == 5) {
            return;
        }

        switch (escolha) {
            case 1:
                editaNomeMercado(indice - 1);
                break;
            case 2:
                editaCNPJMercado(indice - 1);
                break;
            case 3:
                editaBairroMercado(indice- 1);
                break;
            case 4:
                editaCidadeMercado(indice- 1);
                break;

        }
    }

    public static void editaNomeMercado(int indice) {
        INPUT.nextLine();
        System.out.println("Digite o novo nome do mercado");
        String nome = INPUT.nextLine().toUpperCase();

        if (nome.isEmpty()) {
            System.out.println("Não é possível cadastrar um nome vazio");
            return;
        }
        for (Mercado m : Dados.mercados) {
            if (m.getNome().equals(nome)) {
                System.out.println("Não é possível cadastrar nomes duplicados");
                return;
            }
        }
        Dados.mercados.get(indice).setNome(nome);
        System.out.println("Nome alterado com sucesso!");
    }

    public static void editaCNPJMercado(int indice) {
        INPUT.nextLine();

        System.out.println("Digite o novo CNPJ");
        String CNPJ = INPUT.nextLine().toUpperCase();

        if (CNPJ.isEmpty()) {
            System.out.println("Não é possível cadastrar um CNPJ vazio");
            return;
        }
        for (Mercado m : Dados.mercados) {
            if (m.getCNPJ().equals(CNPJ)) {
                System.out.println("Não é possível cadastrar CNPJ duplicado");
                return;
            }
        }
        Dados.mercados.get(indice).setCNPJ(CNPJ);
        System.out.println("CNPJ alterado com sucesso!!!");
    }

    public static void editaBairroMercado(int indice) {
        INPUT.nextLine();
        System.out.println("Digite o bairro");
        String bairro = INPUT.nextLine().toUpperCase();

        if (bairro.isEmpty()) {
            System.out.println("Não é possível cadastrar um bairro com nome vazio");
            return;
        }

        Dados.mercados.get(indice).setBairro(bairro);
    }

    public static void editaCidadeMercado(int indice) {
        INPUT.nextLine();
        System.out.println("Digite o nome da cidade");
        String cidade = INPUT.nextLine().toUpperCase();

        if (cidade.isEmpty()) {
            System.out.println("Não uma cidade com nome vazio");
            return;
        }
        Dados.mercados.get(indice).setCidade(cidade);
        System.out.println("Cidade alterada com sucesso");

    }
}
