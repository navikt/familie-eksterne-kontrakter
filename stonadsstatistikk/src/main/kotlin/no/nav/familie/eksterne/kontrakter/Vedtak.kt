package no.nav.familie.eksterne.kontrakter

data class Vedtak(val fagsakId: String,
                  val behandlingsId: String,
                  val tidspunktVedtak: LocalDate,
                  val personIdent: String,
                  val ensligForsørger: Boolean,
                  val utbetalingsperioder: List<Utbetalingsperiode>)

data class Utbetalingsperiode(val klassekode: String,
                              val hjemmel: String,
                              val beløp: Int,
                              val utbetalingsfrekvens: String,
                              val stønadFom: LocalDate,
                              val stønadTom: LocalDate,
                              val personer: List<Person>)

data class Person(val personIdent: String,
                  val rolle: String,
                  val statsborgerskap: String,
                  val bostedsland: String,
                  val primærland: String,
                  val sekundærland: String,
                  val delingsprosentOmsorg: Int,
                  val delingsprosentYtelse: Int,
                  val annenpartPersonident: String,
                  val annenpartStatsborgerskap: String,
                  val annenpartBostedsland: String)