CREATE TABLE IF NOT EXISTS public.profile (
    id SERIAL PRIMARY KEY,
    name character varying(255) NOT NULL,
    email character varying(255) UNIQUE NOT NULL,
    created_dt time without time zone NOT NULL,
    updated_dt time without time zone
);

CREATE TABLE IF NOT EXISTS public.post (
    id SERIAL PRIMARY KEY,
    content character varying(255) NOT NULL,
    posted_by integer NOT NULL,
    created_dt time without time zone NOT NULL,
    updated_dt time without time zone,
    CONSTRAINT posted_by_fk
          FOREIGN KEY(posted_by)
    	  REFERENCES profile(id)
    	  ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS public.comment (
    id SERIAL PRIMARY KEY,
    content character varying(255) NOT NULL,
    post_id integer NOT NULL,
    commented_by integer NOT NULL,
    parent_comment_id integer,
    created_dt time without time zone NOT NULL,
    updated_dt time without time zone,
    CONSTRAINT commented_by_fk
          FOREIGN KEY(commented_by)
    	  REFERENCES profile(id)
    	  ON DELETE CASCADE,
    CONSTRAINT post_fk
          FOREIGN KEY(post_id)
          REFERENCES post(id)
          ON DELETE CASCADE,
    CONSTRAINT parent_comment_fk
          FOREIGN KEY(parent_comment_id)
          REFERENCES comment(id)
          ON DELETE CASCADE
);

CREATE TYPE action_type AS ENUM ('LIKE', 'DISLIKE');

CREATE TABLE IF NOT EXISTS public.viewer_action (
    id SERIAL PRIMARY KEY,
    action_by integer NOT NULL,
    current_action action_type,
    comment_id integer NOT NULL,
    created_dt time without time zone NOT NULL,
    updated_dt time without time zone,
    CONSTRAINT action_by_fk
          FOREIGN KEY(action_by)
    	  REFERENCES profile(id)
    	  ON DELETE CASCADE,
    CONSTRAINT comment_fk
          FOREIGN KEY(comment_id)
          REFERENCES comment(id)
          ON DELETE CASCADE
);