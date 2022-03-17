const listDocs = () => {
    return {
        docs: [],
        formMessage: '',
        async getAll() {
            this.formMessage = '';
            return fetchApi(
                '/all',
                'GET',
                '',
                async (response) => {
                    this.formMessage = 'Documents chargés';
                    return response.json();
                },
                (e) => {
                    this.formMessage = 'Qqch n\'a pas fonctionné ...';
                }
            );
        },
        async deleteDoc(id) {
            this.formMessage = '';
            fetchApi(
                '/Delete/' + id,
                'DELETE',
                '',
                (response) => {
                    this.formMessage = 'Document supprimé !';
                    this.docs = this.docs.filter(e => e.idDocument != id);
                },
                (e) => {
                    this.formMessage = 'Qqch n\'a pas fonctionné ...';
                }
            );
        }
    };
}