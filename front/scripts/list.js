const listDocs = () => {
  return {
    page: 0,
    nbItems: 5,
    first: true,
    last: true,
    max: 0,
    docs: [],
    formMessage: '',
    texteRecherche: '',
    async getAll() {
      console.log(this.texteRecherche);
      this.formMessage = '';
      return fetchApi(
        this.texteRecherche == '' ? '/all/page' : '/Document/Recherche/Page',
        'GET',
        {
          numPage: this.page,
          nbElm: this.nbItems,
          nom: this.texteRecherche
        },
        async (response) => {
          const res = await response.json();
          this.formMessage = res.totalElements+' archives trouvées';
          this.first = res.first;
          this.last = res.last;
          this.max = res.totalPages - 1;
          return res.content;
        },
        (e) => {
          this.formMessage = 'Qqch n\'a pas fonctionné ...';
        }
      );
    },
    async deleteDoc(id, nom) {
      if (confirm("Êtes-vous sûr de vouloir supprimer " + nom + " ?")) {
        this.formMessage = '';
        fetchApi(
          '/Delete/' + id,
          'DELETE',
          '',
          async (response) => {
            this.formMessage = 'Document supprimé !';
            this.docs = await this.getAll();
          },
          (e) => {
            this.formMessage = 'Qqch n\'a pas fonctionné ...';
          }
        );
      }
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
    },
    async firstPage() {
      if (this.first) return;
      this.page = 0;
      this.docs = await this.getAll();
    },
    async lastPage() {
      if (this.last) return;
      this.page = this.page = this.max;
      this.docs = await this.getAll();
    },
    async recherche() {
      alert(this.texteRecherche);
    }
  };
}