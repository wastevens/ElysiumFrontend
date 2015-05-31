alter table Backgrounds drop foreign key PlayerCharacter_Backgrounds_FK;
alter table Backgrounds_Focuses drop foreign key CharacterBackground_focuses_FK;
alter table Disciplines drop foreign key PlayerCharacter_Disciplines_FK;
alter table Flaws drop foreign key PlayerCharacter_Flaws_FK;
alter table Merits drop foreign key PlayerCharacter_Merits_FK;
alter table Patronage_payments drop foreign key Patronage_PatronagePaymentReceipt_FK;
alter table PlayerCharacter_AppliedTraitChanges drop foreign key AppliedTraitChanges_PlayerCharacter_FK;
alter table PlayerCharacter_AppliedTraitChanges drop foreign key PlayerCharacter_AppliedTraitChanges_FK;
alter table PlayerCharacter_ExperienceAward drop foreign key ExperienceAwards_PlayerCharacter_FK;
alter table PlayerCharacter_ExperienceAward drop foreign key PlayerCharacter_ExperienceAwards_FK;
alter table PlayerCharacter_RequestedTraitChanges drop foreign key RequestedTraitChanges_PlayerCharacter_FK;
alter table PlayerCharacter_RequestedTraitChanges drop foreign key PlayerCharacter_RequestedTraitChanges_FK;
alter table PlayerCharacter_elderPowers drop foreign key PlayerCharacter_ElderPowers_FK;
alter table PlayerCharacter_inClanDisciplines drop foreign key PlayerCharacter_InClanDisciplines_FK;
alter table PlayerCharacter_mentalAttrbuteFocuses drop foreign key PlayerCharacter_MentalAttributeFocuses_FK;
alter table PlayerCharacter_necromanticRituals drop foreign key PlayerCharacter_CharacterNecromanticRituals_FK;
alter table PlayerCharacter_physicalAttributeFocuses drop foreign key PlayerCharacter_PhysicalAttributeFocuses_FK;
alter table PlayerCharacter_playerStatusChanges drop foreign key PlayerCharacter_PlayerStatusChanges_FK;
alter table PlayerCharacter_socialAttributeFocuses drop foreign key PlayerCharacter_SocialAttributeFocuses_FK;
alter table PlayerCharacter_techniques drop foreign key PlayerCharacter_Techniques_FK;
alter table PlayerCharacter_thaumaturgicalRituals drop foreign key PlayerCharacter_CharacterThaumaturgicalRituals_FK;
alter table Skills drop foreign key PlayerCharacter_Skills_FK;
alter table Skills_Focuses drop foreign key CharacterSkill_focuses_FK;
alter table Status drop foreign key PlayerCharacter_Status_FK;
alter table TraitChange_focuses drop foreign key TraitQualities_focuses_FK;
alter table TraitChanges drop foreign key TraitChange_ChildTraitChange_FK;
alter table Troupe_PlayerCharacters drop foreign key PlayerCharacters_Troupe_FK;
alter table Troupe_PlayerCharacters drop foreign key Troupe_PlayerCharacters_FK;
alter table Troupe_StorytellerUsers drop foreign key StorytellerUsers_Troupe_FK;
alter table Troupe_StorytellerUsers drop foreign key Troupe_StorytellerUsers_FK;
alter table User_Patronage drop foreign key User_Patronage_FK;
alter table User_Patronage drop foreign key Patronage_User_FK;
alter table User_PlayerCharacters drop foreign key PlayerCharacters_User_FK;
alter table User_PlayerCharacters drop foreign key User_PlayerCharacters_FK;
alter table User_roles drop foreign key User_Roles_FK;
drop table if exists AttributeValue;
drop table if exists Backgrounds;
drop table if exists Backgrounds_Focuses;
drop table if exists Disciplines;
drop table if exists ExperienceAward;
drop table if exists Flaws;
drop table if exists Merits;
drop table if exists Patronage;
drop table if exists Patronage_payments;
drop table if exists PlayerCharacter;
drop table if exists PlayerCharacter_AppliedTraitChanges;
drop table if exists PlayerCharacter_ExperienceAward;
drop table if exists PlayerCharacter_RequestedTraitChanges;
drop table if exists PlayerCharacter_elderPowers;
drop table if exists PlayerCharacter_inClanDisciplines;
drop table if exists PlayerCharacter_mentalAttrbuteFocuses;
drop table if exists PlayerCharacter_necromanticRituals;
drop table if exists PlayerCharacter_physicalAttributeFocuses;
drop table if exists PlayerCharacter_playerStatusChanges;
drop table if exists PlayerCharacter_socialAttributeFocuses;
drop table if exists PlayerCharacter_techniques;
drop table if exists PlayerCharacter_thaumaturgicalRituals;
drop table if exists Skills;
drop table if exists Skills_Focuses;
drop table if exists Status;
drop table if exists TraitChange_focuses;
drop table if exists TraitChanges;
drop table if exists Troupe;
drop table if exists Troupe_PlayerCharacters;
drop table if exists Troupe_StorytellerUsers;
drop table if exists User;
drop table if exists UserPasswordResetToken;
drop table if exists User_Patronage;
drop table if exists User_PlayerCharacters;
drop table if exists User_roles;
drop table if exists ID_Sequences;
create table AttributeValue (attribute_type varchar(31) not null, id integer not null, value integer, primary key (id));
create table Backgrounds (id integer not null, rating integer, specialization varchar(255), background integer, character_id integer, primary key (id));
create table Backgrounds_Focuses (CharacterBackground_id integer not null, focuses varchar(255));
create table Disciplines (id integer not null, rating integer, trait integer not null, character_id integer, primary key (id));
create table ExperienceAward (id integer not null, awardedFor varchar(255), awardedOn datetime, experience integer, primary key (id));
create table Flaws (id integer not null, specialization varchar(255), trait integer not null, character_id integer, primary key (id));
create table Merits (id integer not null, specialization varchar(255), trait integer not null, character_id integer, primary key (id));
create table Patronage (id integer not null, expiration datetime, original_username varchar(255), year integer, primary key (id));
create table Patronage_payments (Patronage_id integer not null, payment_amount decimal(19,2), payment_date datetime, payment_receipt_identifier varchar(255), payment_type integer, order_by integer not null, primary key (Patronage_id, order_by));
create table PlayerCharacter (id integer not null, approvalStatus integer, awardedXp integer, baseXp integer, bloodline integer, clan integer, groundXp integer, mental_attribute integer, name varchar(255), physical_attribute integer, primary_necromantic_path integer, primary_thaumaturgical_path integer, setting integer, social_attribute integer, appliedSpentXp integer, pendingSpentXp integer, primary key (id));
create table PlayerCharacter_AppliedTraitChanges (PlayerCharacter_id integer not null, appliedTraitChanges_id bigint not null, order_by integer not null, primary key (PlayerCharacter_id, order_by));
create table PlayerCharacter_ExperienceAward (PlayerCharacter_id integer not null, experienceAwards_id integer not null);
create table PlayerCharacter_RequestedTraitChanges (PlayerCharacter_id integer not null, requesedTraitChanges_id bigint not null, order_by integer not null, primary key (PlayerCharacter_id, order_by));
create table PlayerCharacter_elderPowers (PlayerCharacter_id integer not null, elderPowers integer);
create table PlayerCharacter_inClanDisciplines (PlayerCharacter_id integer not null, inClanDisciplines integer, order_by integer not null, primary key (PlayerCharacter_id, order_by));
create table PlayerCharacter_mentalAttrbuteFocuses (PlayerCharacter_id integer not null, mentalAttrbuteFocuses integer);
create table PlayerCharacter_necromanticRituals (PlayerCharacter_id integer not null, necromanticRituals integer);
create table PlayerCharacter_physicalAttributeFocuses (PlayerCharacter_id integer not null, physicalAttributeFocuses integer);
create table PlayerCharacter_playerStatusChanges (PlayerCharacter_id integer not null, changedOn datetime, status integer, order_by integer not null, primary key (PlayerCharacter_id, order_by));
create table PlayerCharacter_socialAttributeFocuses (PlayerCharacter_id integer not null, socialAttributeFocuses integer);
create table PlayerCharacter_techniques (PlayerCharacter_id integer not null, techniques integer);
create table PlayerCharacter_thaumaturgicalRituals (PlayerCharacter_id integer not null, thaumaturgicalRituals integer);
create table Skills (id integer not null, rating integer, specialization varchar(255), skill integer, character_id integer, primary key (id));
create table Skills_Focuses (CharacterSkill_id integer not null, focuses varchar(255));
create table Status (id integer not null, specialization varchar(255), trait integer not null, character_id integer, primary key (id));
create table TraitChange_focuses (TraitChange_id bigint not null, focuses varchar(255));
create table TraitChanges (id bigint not null, cost integer, rating integer, specialization varchar(255), remove bit, traitOrdinal integer, traitTypeOrdinal integer, child_id bigint, primary key (id));
create table Troupe (id integer not null, deleted_at datetime, name varchar(255), venue integer, primary key (id));
create table Troupe_PlayerCharacters (troupe_id integer not null, playerCharacter_id integer not null, primary key (troupe_id, playerCharacter_id));
create table Troupe_StorytellerUsers (troupe_id integer not null, user_id integer not null, primary key (troupe_id, user_id));
create table User (id integer not null, email varchar(255), firstName varchar(255), lastName varchar(255), password varchar(255), primary key (id));
create table UserPasswordResetToken (id integer not null, email varchar(255), expiresAt datetime, resetToken varchar(255), primary key (id));
create table User_Patronage (patronage_id integer, user_id integer, primary key (patronage_id));
create table User_PlayerCharacters (user_id integer not null, playerCharacter_id integer not null, primary key (user_id, playerCharacter_id));
create table User_roles (User_id integer not null, roles integer);
alter table PlayerCharacter_AppliedTraitChanges add constraint PlayerCharacter_AppliedTraitChanges_UC  unique (appliedTraitChanges_id);
alter table PlayerCharacter_ExperienceAward add constraint PlayerCharacter_ExperienceAwards_UC  unique (experienceAwards_id);
alter table PlayerCharacter_RequestedTraitChanges add constraint PlayerCharacter_RequestedTraitChanges_UC  unique (requesedTraitChanges_id);
alter table User_Patronage add constraint User_Patronage_UC  unique (patronage_id, user_id);
alter table Backgrounds add constraint PlayerCharacter_Backgrounds_FK foreign key (character_id) references PlayerCharacter (id);
alter table Backgrounds_Focuses add constraint CharacterBackground_focuses_FK foreign key (CharacterBackground_id) references Backgrounds (id);
alter table Disciplines add constraint PlayerCharacter_Disciplines_FK foreign key (character_id) references PlayerCharacter (id);
alter table Flaws add constraint PlayerCharacter_Flaws_FK foreign key (character_id) references PlayerCharacter (id);
alter table Merits add constraint PlayerCharacter_Merits_FK foreign key (character_id) references PlayerCharacter (id);
alter table Patronage_payments add constraint Patronage_PatronagePaymentReceipt_FK foreign key (Patronage_id) references Patronage (id);
alter table PlayerCharacter_AppliedTraitChanges add constraint AppliedTraitChanges_PlayerCharacter_FK foreign key (appliedTraitChanges_id) references TraitChanges (id);
alter table PlayerCharacter_AppliedTraitChanges add constraint PlayerCharacter_AppliedTraitChanges_FK foreign key (PlayerCharacter_id) references PlayerCharacter (id);
alter table PlayerCharacter_ExperienceAward add constraint ExperienceAwards_PlayerCharacter_FK foreign key (experienceAwards_id) references ExperienceAward (id);
alter table PlayerCharacter_ExperienceAward add constraint PlayerCharacter_ExperienceAwards_FK foreign key (PlayerCharacter_id) references PlayerCharacter (id);
alter table PlayerCharacter_RequestedTraitChanges add constraint RequestedTraitChanges_PlayerCharacter_FK foreign key (requesedTraitChanges_id) references TraitChanges (id);
alter table PlayerCharacter_RequestedTraitChanges add constraint PlayerCharacter_RequestedTraitChanges_FK foreign key (PlayerCharacter_id) references PlayerCharacter (id);
alter table PlayerCharacter_elderPowers add constraint PlayerCharacter_ElderPowers_FK foreign key (PlayerCharacter_id) references PlayerCharacter (id);
alter table PlayerCharacter_inClanDisciplines add constraint PlayerCharacter_InClanDisciplines_FK foreign key (PlayerCharacter_id) references PlayerCharacter (id);
alter table PlayerCharacter_mentalAttrbuteFocuses add constraint PlayerCharacter_MentalAttributeFocuses_FK foreign key (PlayerCharacter_id) references PlayerCharacter (id);
alter table PlayerCharacter_necromanticRituals add constraint PlayerCharacter_CharacterNecromanticRituals_FK foreign key (PlayerCharacter_id) references PlayerCharacter (id);
alter table PlayerCharacter_physicalAttributeFocuses add constraint PlayerCharacter_PhysicalAttributeFocuses_FK foreign key (PlayerCharacter_id) references PlayerCharacter (id);
alter table PlayerCharacter_playerStatusChanges add constraint PlayerCharacter_PlayerStatusChanges_FK foreign key (PlayerCharacter_id) references PlayerCharacter (id);
alter table PlayerCharacter_socialAttributeFocuses add constraint PlayerCharacter_SocialAttributeFocuses_FK foreign key (PlayerCharacter_id) references PlayerCharacter (id);
alter table PlayerCharacter_techniques add constraint PlayerCharacter_Techniques_FK foreign key (PlayerCharacter_id) references PlayerCharacter (id);
alter table PlayerCharacter_thaumaturgicalRituals add constraint PlayerCharacter_CharacterThaumaturgicalRituals_FK foreign key (PlayerCharacter_id) references PlayerCharacter (id);
alter table Skills add constraint PlayerCharacter_Skills_FK foreign key (character_id) references PlayerCharacter (id);
alter table Skills_Focuses add constraint CharacterSkill_focuses_FK foreign key (CharacterSkill_id) references Skills (id);
alter table Status add constraint PlayerCharacter_Status_FK foreign key (character_id) references PlayerCharacter (id);
alter table TraitChange_focuses add constraint TraitQualities_focuses_FK foreign key (TraitChange_id) references TraitChanges (id);
alter table TraitChanges add constraint TraitChange_ChildTraitChange_FK foreign key (child_id) references TraitChanges (id);
alter table Troupe_PlayerCharacters add constraint PlayerCharacters_Troupe_FK foreign key (playerCharacter_id) references PlayerCharacter (id);
alter table Troupe_PlayerCharacters add constraint Troupe_PlayerCharacters_FK foreign key (troupe_id) references Troupe (id);
alter table Troupe_StorytellerUsers add constraint StorytellerUsers_Troupe_FK foreign key (user_id) references User (id);
alter table Troupe_StorytellerUsers add constraint Troupe_StorytellerUsers_FK foreign key (troupe_id) references Troupe (id);
alter table User_Patronage add constraint User_Patronage_FK foreign key (patronage_id) references Patronage (id);
alter table User_Patronage add constraint Patronage_User_FK foreign key (user_id) references User (id);
alter table User_PlayerCharacters add constraint PlayerCharacters_User_FK foreign key (playerCharacter_id) references PlayerCharacter (id);
alter table User_PlayerCharacters add constraint User_PlayerCharacters_FK foreign key (user_id) references User (id);
alter table User_roles add constraint User_Roles_FK foreign key (User_id) references User (id);
create table ID_Sequences ( sequence_name varchar(255),  sequence_next_hi_value bigint );
