import pdfMake from 'pdfmake/build/pdfmake'
import pdfFonts from 'pdfmake/build/vfs_fonts'

function clientesPDF(clientes) {

    pdfMake.vfs = pdfFonts.pdfMake.vfs;

    const reportTitle = [
        {
            text: 'Relatório de Clientes',
            fontSize: 15,
            bold: true,
            alignment: 'center',
            margin: [0, 20, 0, 20] //left, top, right, bottom
        }
    ];

    const dados = clientes.map((cliente) => {
        return[
            {text: cliente.id, fontSize: 9, margin: [0, 2, 0, 2]},
            {text: cliente.name, fontSize: 9, margin: [0, 2, 0, 2]},
            {text: cliente.rua, fontSize: 9, margin: [0, 2, 0, 2]},
            {text: cliente.numero, fontSize: 9, margin: [0, 2, 0, 2]},
            {text: cliente.bairro, fontSize: 9, margin: [0, 2, 0, 2]},
            {text: cliente.cidade, fontSize: 9, margin: [0, 2, 0, 2]},
            {text: cliente.estado, fontSize: 9, margin: [0, 2, 0, 2]},
            {text: cliente.cep, fontSize: 9, margin: [0, 2, 0, 2]},
        ]
    });

    const details = [
        {
            table: {
                    headerRows: 1,
                    widths: ['*', '*', '*', '*', '*', '*', '*', '*'],
                    body: [
                        [
                            {text: 'Id', style: 'tableHeader', fontSize: 10, bold: true},
                            {text: 'Nome', style: 'tableHeader', fontSize: 10, bold: true},
                            {text: 'Rua', style: 'tableHeader', fontSize: 10, bold: true},
                            {text: 'Numero', style: 'tableHeader', fontSize: 10, bold: true},
                            {text: 'Bairro', style: 'tableHeader', fontSize: 10, bold: true},
                            {text: 'Cidade', style: 'tableHeader', fontSize: 10, bold: true},
                            {text: 'Estado', style: 'tableHeader', fontSize: 10, bold: true},
                            {text: 'Cep', style: 'tableHeader', fontSize: 10, bold: true}
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
                margin: [0, 10, 20, 0] //left, top, right, bottom
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

export default clientesPDF;