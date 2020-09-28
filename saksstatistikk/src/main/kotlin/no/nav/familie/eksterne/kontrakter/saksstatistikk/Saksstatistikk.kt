package no.nav.familie.eksterne.kontrakter.saksstatistikk

import java.time.LocalDate
import java.time.ZonedDateTime

data class BehandlingDVH(
        val funksjonellTid: ZonedDateTime,
        val tekniskTid: ZonedDateTime,
        val mottattDato: ZonedDateTime,
        val registrertDato: ZonedDateTime,
        val vedtaksDato: LocalDate? = null,
        val behandlingId: String,
        val relatertBehandlingId: String? = null,
        val sakId: String,
        val vedtakId: String? = null,
        val behandlingType: String,
        val behandlingStatus: String,
        val resultat: String? = null,
        val resultatBegrunnelse: String? = null,
        val utenlandstilsnitt: String,
        val behandlingTypeBeskrivelse: String? = null,
        val behandlingStatusBeskrivelse: String? = null,
        val resultatBeskrivelse: String? = null,
        val resultatBegrunnelseBeskrivelse: List<String> = emptyList(),
        val utenlandstilsnittBeskrivelse: String? = null,
        val beslutter: String? = null,
        val saksbehandler: String? = null,
        val behandlingOpprettetAv: String? = null,
        val behandlingOpprettetType: String? = null,
        val behandlingOpprettetTypeBeskrivelse: String? = null,
        val ansvarligEnhetKode: String,
        val ansvarligEnhetType: String,
        val behandlendeEnhetKode: String,
        val behandlendeEnhetType: String,
        val totrinnsbehandling: Boolean,
        val avsender: String,
        val versjon: String
)

data class SakDVH(
        val funksjonellTid: ZonedDateTime,
        val tekniskTid: ZonedDateTime,
        val opprettetDato: LocalDate,
        val sakId: String,
        val aktorId: Long,
        val aktorer: List<AktørDVH>? = emptyList(),
        val ytelseType: String,
        val underType: String? = null,
        val sakStatus: String,
        val ytelseTypeBeskrivelse: String? = null,
        val underTypeBeskrivelse: String? = null,
        val sakStatusBeskrivelse: String? = null,
        val avsender: String,
        val versjon: String
)

data class AktørDVH(
        val aktorId: Long,
        val rolle: String,
        val rolleBeskrivelse: String
)
