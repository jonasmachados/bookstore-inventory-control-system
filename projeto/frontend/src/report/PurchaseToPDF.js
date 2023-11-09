import pdfMake from 'pdfmake/build/pdfmake'
import pdfFonts from 'pdfmake/build/vfs_fonts'
import { convertNumberToCurrency } from "../utils/convertNumberToCurrency";

function PurchaseToPDF(purchaseList) {

    pdfMake.vfs = pdfFonts.pdfMake.vfs;

    const reportTitle = [
        {
            text: 'Relatório de Compras',
            fontSize: 15,
            bold: true,
            alignment: 'center',
            margin: [0, 20, 0, 20]
        }
    ];

    const dados = purchaseList.map((purchase) => {
        return [
            {
                text: purchase.id,
                fontSize: 9,
                margin: [0, 2, 0, 2]
            },
            {
                text: purchase.livro.titulo,
                fontSize: 9,
                margin: [0, 2, 0, 2]
            },
            {
                text: purchase.qtdItens,
                fontSize: 9,
                margin: [0, 2, 0, 2]
            },
            {
                text: convertNumberToCurrency(purchase.precoVenda),
                fontSize: 9,
                margin: [0, 2, 0, 2]
            },
            {
                text: convertNumberToCurrency(purchase.total),
                fontSize: 9,
                margin: [0, 2, 0, 2]
            },
        ]
    });

    const details = [
        {
            table: {
                headerRows: 1,
                widths: ['*', '*', '*', '*', '*'],
                body: [
                    [
                        {
                            text: 'Id',
                            style: 'tableHeader',
                            fontSize: 10,
                            bold: true
                        },
                        {
                            text: 'Livro',
                            style: 'tableHeader',
                            fontSize: 10,
                            bold: true
                        },
                        {
                            text: 'Quantidade',
                            style: 'tableHeader',
                            fontSize: 10,
                            bold: true
                        },
                        {
                            text: 'Preço Unitário',
                            style: 'tableHeader',
                            fontSize: 10,
                            bold: true
                        },
                        {
                            text: 'Valor Total',
                            style: 'tableHeader',
                            fontSize: 10,
                            bold: true
                        },
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

export default PurchaseToPDF;