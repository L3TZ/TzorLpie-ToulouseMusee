package toulousemusee


class DemandeVisite {

    String code
    Date dateDebutPeriode
    Date dateFinPeriode
    int nbPersonnes
    String statut
    Date dateDemande

    static hasMany = [musees:Musee]

    static belongsTo = Musee

    static mapping = {
        musees fetch:'join'
    }

    static constraints = {
        nbPersonnes max: 6, min: 1
        dateDebutPeriode validator: {
            Calendar c1 = GregorianCalendar.getInstance()
            c1.set(Calendar.HOUR_OF_DAY, 0);
            c1.set(Calendar.MINUTE, 0);
            c1.set(Calendar.SECOND, 0);
            c1.set(Calendar.MILLISECOND, 0);
            if (!(it >= c1.getTime()))
                return 'demandeVisite.validation.dateDebutPeriode'
        }
        dateFinPeriode validator: {val, obj ->
            if (!val.after(obj.dateDebutPeriode))
                return 'demandeVisite.validation.dateFinPeriode'
        }
    }
}
