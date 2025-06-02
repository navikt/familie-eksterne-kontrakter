package no.nav.familie.eksterne.kontrakter.saksstatistikk

import java.time.LocalDate
import java.time.ZonedDateTime

data class BehandlingDVH(
    val funksjonellTid: ZonedDateTime,
    val tekniskTid: ZonedDateTime,
    val mottattDato: ZonedDateTime,
    val registrertDato: ZonedDateTime,
    val vedtaksDato: LocalDate? = null,
    val funksjonellId: String,
    val behandlingId: String,
    val relatertBehandlingId: String? = null,
    val relatertBehandlingFagsystem: String? = null,
    val sakId: String,
    val vedtakId: String? = null,
    val behandlingType: String,
    val behandlingStatus: String,
    val behandlingKategori: String,
    val behandlingUnderkategori: String? = null,
    val behandlingAarsak: String,
    val automatiskBehandlet: Boolean,
    val resultat: String? = null,
    val utenlandstilsnitt: String,
    val behandlingTypeBeskrivelse: String? = null,
    val behandlingStatusBeskrivelse: String? = null,
    val resultatBeskrivelse: String? = null,
    val resultatBegrunnelser: List<ResultatBegrunnelseDVH> = emptyList(),
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
    val versjon: String,
    val førsteInnvilgedeVilkårsdato: LocalDate? = null,
    val settPaaVent: SettPåVent? = null,
)

data class ResultatBegrunnelseDVH(
    val fom: LocalDate? = null,
    val tom: LocalDate? = null,
    val type: String,
    val vedtakBegrunnelse: String,
)

data class SakDVH(
    val funksjonellTid: ZonedDateTime,
    val tekniskTid: ZonedDateTime,
    val opprettetDato: LocalDate,
    val funksjonellId: String,
    val sakId: String,
    val aktorId: Long,
    val bostedsland: String,
    val aktorer: List<AktørDVH>? = emptyList(),
    val sakStatus: String,
    val avsender: String,
    val versjon: String,
    val ytelseType: String = "BARNETRYGD",
)

data class AktørDVH(
    val aktorId: Long,
    val rolle: String,
    val rolleBeskrivelse: String? = null,
)

data class SettPåVent(
    val frist: ZonedDateTime,
    val tidSattPaaVent: ZonedDateTime,
    val aarsak: String,
)
