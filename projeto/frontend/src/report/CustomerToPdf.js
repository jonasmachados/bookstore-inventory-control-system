import pdfMake from 'pdfmake/build/pdfmake'
import pdfFonts from 'pdfmake/build/vfs_fonts'

function CustomertoPdf(customerList) {

    pdfMake.vfs = pdfFonts.pdfMake.vfs;

    const reportTitle = [
        {
            text: 'Relatório de Clientes',
            fontSize: 15,
            bold: true,
            alignment: 'center',
            margin: [0, 20, 0, 20] 
        }
    ];

    const dados = customerList.map((customer) => {
        return[
            {text: customer.id, fontSize: 9, margin: [0, 2, 0, 2]},
            {text: customer.nome, fontSize: 9, margin: [0, 2, 0, 2]},
            {text: customer.rua, fontSize: 9, margin: [0, 2, 0, 2]},
            {text: customer.numero, fontSize: 9, margin: [0, 2, 0, 2]},
            {text: customer.bairro, fontSize: 9, margin: [0, 2, 0, 2]},
            {text: customer.cidade, fontSize: 9, margin: [0, 2, 0, 2]},
            {text: customer.estado, fontSize: 9, margin: [0, 2, 0, 2]},
            {text: customer.cep, fontSize: 9, margin: [0, 2, 0, 2]},
        ]
    });

    const details = [
        {
            table: {
                    headerRows: 1,
                    widths: ['*', '*', '*', '*', '*', '*', '*', '*'],
                    body: [
                        [
                            {text: 'Id', 
                            style: 'tableHeader', 
                            fontSize: 10,
                             bold: true},
                            {text: 'Nome',
                             style: 'tableHeader', 
                             fontSize: 10,
                              bold: true},
                            {text: 'Rua', 
                            style: 'tableHeader',
                             fontSize: 10, 
                             bold: true},
                            {text: 'Numero', 
                            style: 'tableHeader',
                             fontSize: 10,
                              bold: true},
                            {text: 'Bairro', 
                            style: 'tableHeader', 
                            fontSize: 10,
                             bold: true},
                            {text: 'Cidade',
                             style: 'tableHeader', 
                             fontSize: 10,
                              bold: true},
                            {text: 'Estado',
                             style: 'tableHeader', 
                             fontSize: 10,
                              bold: true},
                            {text: 'Cep', 
                            style: 'tableHeader', 
                            fontSize: 10,
                             bold: true}
                        ],
                        ...dados
                    ]
            },
            margin: [0, 20, 0, 20],
            alignment: 'center',
            layout: 'lightHorizontalLines'
        }
    ];

    function Rodape(currentPage, pageCount) {
        return [
            {
                text: currentPage + ' / ' + pageCount,
                alignment: 'right',
                fontSize: 9,
                margin: [0, 10, 20, 0]
            }
        ]
    }

    const docDefinitios = {
        pageSize: 'A4',
        paggeMargins: [15, 50, 15, 40],
        header: [reportTitle],
        content: [details],
        footer: Rodape
    }

    pdfMake.createPdf(docDefinitios).download();

}

export default CustomertoPdf;