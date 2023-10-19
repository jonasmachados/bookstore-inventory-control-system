import * as yup from 'yup';

export const bookRegistrationSchema = yup.object({
    titulo: yup.string()
        .min(3, 'O título deve ter no mínimo 3 caracteres.')
        .max(50, 'O título não pode ter mais de 50 caracteres.')
        .required('O título é obrigatório.'),

    autor: yup.string()
        .min(3, 'O autor deve ter no mínimo 3 caracteres.')
        .max(50, 'O autor não pode ter mais de 50 caracteres.')
        .required('O autor é obrigatório.'),

    editora: yup.string()
        .min(3, 'A editora deve ter no mínimo 3 caracteres.')
        .max(50, 'A editora não pode ter mais de 50 caracteres.')
        .required('A editora é obrigatória.'),

    linkImg: yup.string()
        .required('O link da imagem é obrigatório.')
        .url('O link da imagem é inválido.'),

    anoPublicacao: yup.string()
        .required('A data de publicação é obrigatória.'),

    estoque: yup.number()
        .typeError('A quantidade de estoque é obrigatória.')
        .integer('A quantidade de estoque deve ser um número inteiro.')
        .required('A quantidade de estoque é obrigatória.'),
});

export const validateForm = async (values, schema) => {
    try {
        await schema.validate(values, { abortEarly: false });
        return {};
    } catch (error) {
        const validationErrors = {};
        error.inner.forEach((err) => {
            validationErrors[err.path] = err.message;
        });
        return validationErrors;
    }
};

export const renderValidationMessage = (validationErrors, field) => {
    if (validationErrors[field]) {
        return (
            <div className="error-message">
                {validationErrors[field]}
            </div>
        );
    }
    return null;
};

export const clearError = (validationErrors, setValidationErrors, field) => {
    if (validationErrors[field]) {
        setValidationErrors((errors) => ({ ...errors, [field]: '' }));
    }
};
