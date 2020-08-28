package no.nav.familie.eksterne.kontrakter

import java.time.LocalDate

data class Vedtak(val fagsakId: String,
                  val behandlingsId: String,
                  val tidspunktVedtak: LocalDate,
                  val personIdent: String,
                  val ensligForsørger: Boolean,
                  val utbetalingsperioder: List<Utbetalingsperiode>)

data class Utbetalingsperiode(val hjemmel: String,
                              val utbetaltPerMnd: Int,
                              val stønadFom: LocalDate,
                              val stønadTom: LocalDate,
                              val utbetalingsDetaljer: List<UtbetalingsDetalj>)

data class UtbetalingsDetalj(val person: Person,
                             val klassekode: String,
                             val utbetaltPrMnd: Int)


data class Person(val personIdent: String,
                  val rolle: String,
                  val statsborgerskap: List<String>,
                  val bostedsland: String,
                  val primærland: String,
                  val sekundærland: String,
                  val delingsprosentOmsorg: Int,
                  val delingsprosentYtelse: Int,
                  val annenpartPersonident: String,
                  val annenpartStatsborgerskap: String,
                  val annenpartBostedsland: String)