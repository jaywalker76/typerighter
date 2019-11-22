package model

import org.languagetool.rules.{RuleMatch => LTRuleMatch}
import play.api.libs.json.{Json, Writes}

import scala.collection.JavaConverters._

object RuleMatch {
  def fromLT(lt: LTRuleMatch): RuleMatch = {
    RuleMatch(
      rule = LTRule.fromLT(lt.getRule),
      fromPos = lt.getFromPos,
      toPos = lt.getToPos,
      message = lt.getMessage,
      shortMessage = Some(lt.getMessage),
      suggestions = lt.getSuggestedReplacements.asScala.toList.map { TextSuggestion(_) }
    )
  }

  implicit val writes: Writes[RuleMatch] = Writes[RuleMatch]((ruleMatch: RuleMatch) => Json.obj(
      "rule" -> BaseRule.toJson(ruleMatch.rule),
      "fromPos"-> ruleMatch.fromPos,
      "toPos" -> ruleMatch.toPos,
      "message" -> ruleMatch.message,
      "shortMessage" -> ruleMatch.shortMessage,
      "suggestions" -> ruleMatch.suggestions,
    )
  )
}

case class RuleMatch(rule: BaseRule,
                     fromPos: Int,
                     toPos: Int,
                     message: String,
                     shortMessage: Option[String] = None,
                     suggestions: List[Suggestion] = List.empty)

