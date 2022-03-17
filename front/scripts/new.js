const newForm = () => {
  return {
    formData: {},
    formMessage: '',
    submitForm() {
      this.formMessage = '';
      fetchApi(
        '/Document',
        'POST',
        this.formData,
        (response) => {
          this.formData = {};
          this.formMessage = 'Nouvel archivage créé !';
        },
        (e) => {
          this.formMessage = 'Qqch n\'a pas fonctionné ...';
        }
      );
    },
    async getTypes() {
      return fetchApi(
        '/type/all',
        'GET',
        '',
        async (response) => {
          return (await response.json()).map(e => e.libType);
        }
      );
    }
  };
}