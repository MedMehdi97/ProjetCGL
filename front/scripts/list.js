const listDocs = () => {
    return {
        page: 0,
        first: true,
        last: true,
        docs: [],
        formMessage: '',
        async getAll() {
            this.formMessage = '';
            return fetchApi(
                '/all/page',
                'GET',
                {
                    numPage: this.page,
                    nbElm: 4
                },
                async (response) => {
                    this.formMessage = 'Documents chargés';
                    const res = await response.json();
                    this.first = res.first;
                    this.last = res.last;
                    return res.content;
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
        },
        async precedent() {
            if (this.first) return;
            this.page--;
            this.docs = await this.getAll();
        },
        async suivant() {
            if (this.last) return;
            this.page++;
            this.docs = await this.getAll();
        }
    };
}