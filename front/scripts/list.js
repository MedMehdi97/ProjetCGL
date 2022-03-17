const listDocs = () => {
    return {
        formData: {},
        formMessage: '',
        async getAll() {
            this.formMessage = '';
            return fetchApi(
                '/all',
                'GET',
                this.formData,
                async (response) => {
                    this.formMessage = 'Documents chargés';
                    const tab = await response.json();
                    return tab;
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
                this.formData,
                (response) => {
                    this.formData = {};
                    this.formMessage = 'Document supprimé !';
                },
                (e) => {
                    this.formMessage = 'Qqch n\'a pas fonctionné ...';
                }
            );
        }
    };
}