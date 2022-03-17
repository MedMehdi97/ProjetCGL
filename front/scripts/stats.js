const statsDocs = () => {
    return {
        formData: {},
        formMessage: '',
        async getAllStats() {
            this.formMessage = '';
            return fetchApi(
                '/Statistiques',
                'GET',
                this.formData,
                async (response) => {
                    this.formMessage = 'Statistiques chargés';
                    const tab = await response.json();
                    console.log(tab);
                    return tab;
                },
                (e) => {
                    this.formMessage = 'Qqch n\'a pas fonctionné ...';
                }
            );
        }
    };
}