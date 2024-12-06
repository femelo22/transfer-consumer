<h1>SQS Consumer - Transferência de Solicitação</h1>

<p>Este projeto implementa um <strong>consumer</strong> em <strong>Java Spring Boot</strong> que consome mensagens de uma <strong>fila SQS</strong> da AWS, processa solicitações de transferência, realiza validações e conclui ou não a operação. O sistema também utiliza <strong>PostgreSQL</strong> como banco de dados para armazenar o status das transações e <strong>Docker</strong> para facilitar o processo de configuração e execução.</p>

<h2 id="tecnologias-utilizadas">Tecnologias Utilizadas</h2>
<ul>
    <li><strong>Java 21</strong>: Linguagem de programação para o desenvolvimento.</li>
    <li><strong>Spring Boot</strong>: Framework para construção da aplicação.</li>
    <li><strong>AWS SQS</strong>: Para o gerenciamento da fila de mensagens.</li>
    <li><strong>PostgreSQL</strong>: Banco de dados relacional para persistência de dados.</li>
    <li><strong>Docker</strong>: Para containerização da aplicação e do banco de dados.</li>
    <li><strong>AWS SDK v2</strong>: Para integração com serviços AWS.</li>
</ul>